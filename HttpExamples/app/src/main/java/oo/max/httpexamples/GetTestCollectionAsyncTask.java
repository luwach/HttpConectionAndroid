package oo.max.httpexamples;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import oo.max.httpexamples.model.TestEntry;
import oo.max.httpexamples.model.TestResponse;
import retrofit2.Call;
import retrofit2.Response;

public class GetTestCollectionAsyncTask extends AsyncTask<Void, Void, List<TestEntry>> {

    private final RetrofitApiClient retrofitApiClient;
    private final TestCollectionDownloadListener testCollectionDownloadListener;

    public GetTestCollectionAsyncTask(TestCollectionDownloadListener testCollectionDownloadListener) {
        this.testCollectionDownloadListener = testCollectionDownloadListener;
        //tworzenie obietu retrofita przy użyciu klasy factory
        RetrofitApiClientFactory retrofitApiClientFactory = new RetrofitApiClientFactory();
        retrofitApiClient = retrofitApiClientFactory.create();
        //żeby stworzyć pole ze zmiennej: ctrl+alt+f -> enter
    }

    @Override
    protected List<TestEntry> doInBackground(Void... voids) {
        try {
            return getTestCollections();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<TestEntry> getTestCollections() throws IOException {
        Call<TestResponse> call = retrofitApiClient.getTestCollection();
        Response<TestResponse> response = call.execute();
        if(response.isSuccessful()) { //http 200+
            TestResponse testResponse = response.body();
            return testResponse.getData();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<TestEntry> testEntries) {
        testCollectionDownloadListener.onDownloaded(testEntries);
    }

    public interface TestCollectionDownloadListener {
        void onDownloaded(List<TestEntry> entries);
    }
}
