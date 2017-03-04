package com.example.lwach.newsandadds.News;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lwach.newsandadds.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public final ImageView image;
    public final TextView title;
    public final TextView content;

    public NewsViewHolder(View view) {
        super(view);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.title = (TextView) view.findViewById(R.id.title);
        this.content = (TextView) view.findViewById(R.id.content);
    }

}
