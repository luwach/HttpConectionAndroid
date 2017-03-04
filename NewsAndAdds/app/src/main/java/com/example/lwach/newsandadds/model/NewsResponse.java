package com.example.lwach.newsandadds.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("data")
    private final List<News> news;

    public NewsResponse(List<News> news) {
        this.news = news;
    }

    public List<News> getNews() {
        return news;
    }

}
