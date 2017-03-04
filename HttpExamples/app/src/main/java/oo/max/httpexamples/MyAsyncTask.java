package oo.max.httpexamples;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String, Void, Long> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(String... voids) {
        try {
            //todo async here
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

}
