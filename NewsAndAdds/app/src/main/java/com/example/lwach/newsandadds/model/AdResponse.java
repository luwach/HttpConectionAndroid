package com.example.lwach.newsandadds.model;

import java.util.List;

/**
 * Created by lwach on 17.02.2017.
 */

public class AdResponse {

    private final List<Ad> data;

    public AdResponse(List<Ad> data) {
        this.data = data;
    }

    public List<Ad> getData() {
        return data;
    }

}
