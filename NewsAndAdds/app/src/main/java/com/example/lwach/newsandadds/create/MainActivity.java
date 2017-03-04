package com.example.lwach.newsandadds.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lwach.newsandadds.Ads.GetNewsAndAdsAsyncTask;
import com.example.lwach.newsandadds.News.NewRecyclerAdapter;
import com.example.lwach.newsandadds.R;
import com.example.lwach.newsandadds.model.Ad;
import com.example.lwach.newsandadds.model.News;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements GetNewsAndAdsAsyncTask.NewsDownloadedListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);

        new GetNewsAndAdsAsyncTask(this).execute();
    }

    @Override
    public void onNewsDownloaded(GetNewsAndAdsAsyncTask.ResultData data) {
        if (data == null) {
            Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
            return;
        }

        List<News> news = data.getNews();
        List<Ad> ads = data.getAds();

        List<ListItem> items = mergeListItems(news, ads);

        NewRecyclerAdapter adapter = new NewRecyclerAdapter(this, items);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<ListItem> mergeListItems(List<News> news, List<Ad> ads) {
        List<ListItem> items = new LinkedList<>();

        for (News aNew : news) {
            items.add(new ListItem.NewsItem(aNew));
        }

        ads = new LinkedList<>(ads);

        Collections.sort(ads, new Comparator<Ad>() {
            @Override
            public int compare(Ad ad, Ad t1) {
                return ad.getIndex() - t1.getIndex();
            }
        });

        int addedCount = 0;
        for (Ad ad : ads) {
            int index = ad.getIndex() + addedCount;
            if(index < items.size()) {
                items.add(index, new ListItem.AdItem(ad));
                addedCount++;
            }
        }

        return items;
    }

}
