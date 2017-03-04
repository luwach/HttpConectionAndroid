package oo.max.httpexamples;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.ResponseBody;
import oo.max.httpexamples.model.TestEntry;
import retrofit2.Call;
import retrofit2.Response;

public class CreateTestEntryAsyncTask extends AsyncTask<TestEntry, Void, Boolean> {

    private final RetrofitApiClient retrofitApiClient;
    private final TestEntryCreatedListener testEntryCreatedListener;

    public CreateTestEntryAsyncTask(TestEntryCreatedListener testEntryCreatedListener) {
        this.testEntryCreatedListener = testEntryCreatedListener;
        RetrofitApiClientFactory retrofitApiClientFactory = new RetrofitApiClientFactory();
        retrofitApiClient = retrofitApiClientFactory.create();
    }

    @Override
    protected Boolean doInBackground(TestEntry... testEntries) {
        try {
            if(testEntries == null || testEntries.length < 1) {
                return false;
            }
            return createTestEntry(testEntries[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean createTestEntry(TestEntry testEntry) throws IOException {
        Call<ResponseBody> call = retrofitApiClient.createTestEntry(testEntry);
        Response<ResponseBody> response = call.execute();

        return response.isSuccessful();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        testEntryCreatedListener.entryCreated(result);
    }

    public interface TestEntryCreatedListener {
        void entryCreated(boolean successful);
    }

}
