package com.example.lwach.newsandadds.apiClient;

import com.example.lwach.newsandadds.model.News;
import com.example.lwach.newsandadds.model.NewsResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NewsService {

    private final NewsAndAdsApiClient newsAndAdsApiClient;

    public NewsService() {
        newsAndAdsApiClient = new NewApiClientFactory().createNewsApiClient();
    }

    public List<News> getNews() throws IOException {
        Call<NewsResponse> call = newsAndAdsApiClient.getNews();
        Response<NewsResponse> response = call.execute();
        return response.body().getNews();
    }

}
