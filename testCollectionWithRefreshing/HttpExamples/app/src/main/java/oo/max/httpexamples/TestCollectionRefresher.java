package oo.max.httpexamples;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import oo.max.httpexamples.model.TestEntry;
import oo.max.httpexamples.model.TestResponse;
import retrofit2.Call;
import retrofit2.Response;

public class TestCollectionRefresher {

    private final RetrofitApiClient retrofitApiClient;
    private final TestCollectionDownloadListener testCollectionDownloadListener;
    private final ScheduledExecutorService executor;

    public TestCollectionRefresher(TestCollectionDownloadListener testCollectionDownloadListener) {
        this.testCollectionDownloadListener = testCollectionDownloadListener;
        retrofitApiClient = new RetrofitApiClientFactory().create();
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRefreshing() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //ta metoda wykonana w wątku w tle co 5 sekund, patrz parametry dalej
                getTestCollections();
            }
        }, 0, 5, TimeUnit.SECONDS); //to mówi jak często ma się wykonywać
    }

    private void getTestCollections() {
        try {
            Log.e("REFRESHER", "REFRESHING DATA!");
            //to leci w tle, tak jak doInBackground w asyncTasksu
            Call<TestResponse> call = retrofitApiClient.getTestCollection();
            Response<TestResponse> response = call.execute();
            if (response.isSuccessful()) { //http 200+
                TestResponse testResponse = response.body();
                List<TestEntry> data = testResponse.getData();

                postDataDownloaded(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postDataDownloaded(final List<TestEntry> data) {
        //przechodze do wątku UI
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //ta metoda będzie wykonana w wątku głównym, tak jak onPostExecute
                testCollectionDownloadListener.onDownloaded(data);
            }
        });
    }

    public void close() {
        executor.shutdown();
    }

}
