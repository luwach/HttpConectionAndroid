package com.example.lwach.newsandadds.model;

/**
 * Created by lwach on 17.02.2017.
 */

public class Ad {

    private final String adContent;
    private final int index;

    public Ad(String adContent, int index) {
        this.adContent = adContent;
        this.index = index;
    }

    public String getAdContent() {
        return adContent;
    }

    public int getIndex() {
        return index;
    }
}
