package com.example.lwach.newsandadds.Ads;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lwach.newsandadds.R;

public class AdViewHolder extends RecyclerView.ViewHolder {

    public final TextView adContent;

    public AdViewHolder(View itemView) {
        super(itemView);

        adContent = (TextView) itemView.findViewById(R.id.ad_content);
    }
}
