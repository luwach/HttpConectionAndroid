package com.example.lwach.newsandadds.create;

import com.example.lwach.newsandadds.model.Ad;
import com.example.lwach.newsandadds.model.News;

public abstract class ListItem<T> {

    private final T data;
    private final int itemType;

    protected ListItem(T data, int itemType) {
        this.data = data;
        this.itemType = itemType;
    }

    public T getData() {
        return data;
    }

    public int getItemType() {
        return itemType;
    }

    public static class NewsItem extends ListItem<News> {

        public NewsItem(News data) {
            super(data, 1);
        }

    }

    public static class AdItem extends ListItem<Ad> {

        public AdItem(Ad data) {
            super(data, 2);
        }

    }

}
