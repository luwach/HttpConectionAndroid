package com.example.lwach.newsandadds.News;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lwach.newsandadds.Ads.AdViewHolder;
import com.example.lwach.newsandadds.R;
import com.example.lwach.newsandadds.create.ListItem;
import com.example.lwach.newsandadds.model.Ad;
import com.example.lwach.newsandadds.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    private final LayoutInflater layoutInflater;
    private List<ListItem> items;

    public NewRecyclerAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = layoutInflater.inflate(R.layout.view_news_item, parent, false);
            return new NewsViewHolder(view);
        }
        if (viewType == 2) {
            View view = layoutInflater.inflate(R.layout.view_ad_item, parent, false);
            return new AdViewHolder(view);
        }

        throw new RuntimeException("ADAPTER: unknown view type!");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == 1) {

            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;

            News news = (News) items.get(position).getData();

            newsViewHolder.title.setText(news.getTitle());
            newsViewHolder.content.setText(news.getContent());

            Picasso.with(context)
                    .load(news.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(newsViewHolder.image);
        }

        if (viewType == 2) {

            AdViewHolder adViewHolder = (AdViewHolder) holder;

            Ad ad = (Ad) items.get(position).getData();

            adViewHolder.adContent.setText(ad.getAdContent());

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
