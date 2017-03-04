package com.example.lwach.newsandadds.apiClient;

import com.example.lwach.newsandadds.model.Ad;
import com.example.lwach.newsandadds.model.AdResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AdsService {

    private final NewsAndAdsApiClient newsAndAdsApiClient;

    public AdsService() {
        newsAndAdsApiClient = new NewApiClientFactory().createNewsApiClient();
    }

    public List<Ad> getAds() throws IOException {
        Call<AdResponse> call = newsAndAdsApiClient.getAds();
        Response<AdResponse> response = call.execute();
        return response.body().getData();
    }

}
