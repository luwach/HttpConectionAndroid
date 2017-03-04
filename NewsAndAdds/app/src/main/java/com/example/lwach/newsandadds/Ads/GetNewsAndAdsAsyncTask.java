package com.example.lwach.newsandadds.Ads;

import android.os.AsyncTask;

import com.example.lwach.newsandadds.apiClient.AdsService;
import com.example.lwach.newsandadds.apiClient.NewApiClientFactory;
import com.example.lwach.newsandadds.apiClient.NewsAndAdsApiClient;
import com.example.lwach.newsandadds.model.Ad;
import com.example.lwach.newsandadds.model.News;
import com.example.lwach.newsandadds.model.NewsResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetNewsAndAdsAsyncTask extends AsyncTask<Void, Void, GetNewsAndAdsAsyncTask.ResultData> {

    private final NewsDownloadedListener newsDownloadedListener;
    private final AdsService adsService;
    private final NewsAndAdsApiClient newsAndAdsApiClient;

    public GetNewsAndAdsAsyncTask(NewsDownloadedListener newsDownloadedListener) {
        this.newsDownloadedListener = newsDownloadedListener;
        newsAndAdsApiClient = new NewApiClientFactory().createNewsApiClient();
        adsService = new AdsService();
    }

    @Override
    protected ResultData doInBackground(Void... voids) {
        try {
            List<News> news = getNews();
            List<Ad> ads = adsService.getAds();

            return new ResultData(news, ads);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<News> getNews() throws IOException {
        Call<NewsResponse> call = newsAndAdsApiClient.getNews();
        Response<NewsResponse> response = call.execute();
        return response.body().getNews();
    }

    @Override
    protected void onPostExecute(ResultData data) {
        newsDownloadedListener.onNewsDownloaded(data);
    }

    public interface NewsDownloadedListener {
        void onNewsDownloaded(ResultData data);
    }

    public static class ResultData {
        private final List<News> news;
        private final List<Ad> ads;

        public ResultData(List<News> news, List<Ad> ads) {
            this.news = news;
            this.ads = ads;
        }

        public List<News> getNews() {
            return news;
        }

        public List<Ad> getAds() {
            return ads;
        }
    }

}
