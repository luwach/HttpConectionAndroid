package com.example.lwach.newsandadds.apiClient;

import com.example.lwach.newsandadds.model.AdResponse;
import com.example.lwach.newsandadds.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsAndAdsApiClient {

    @GET("/plugin/test.news")
    @Headers("X-BAASBOX-APPCODE: 1234567890")
    Call<NewsResponse> getNews();

    @GET("/plugin/test.ads")
    @Headers("X-BAASBOX-APPCODE: 1234567890")
    Call<AdResponse> getAds();

}
