package com.mac.nytimes.viewholders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mac.nytimes.R;
import com.mac.nytimes.activities.MultimediaActivity;
import com.mac.nytimes.commons.AppConstants;
import com.mac.nytimes.models.Result;

import org.w3c.dom.Text;

public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final Context mContext;
    private final TextView title;
    private final TextView url;
    private final TextView abstractText;
    private final TextView byLine;
    private Result result;

    public NewsViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        itemView.setOnClickListener(this);
        title = (TextView) itemView.findViewById(R.id.title);
        abstractText = (TextView) itemView.findViewById(R.id.abstracttxt);
        url = (TextView) itemView.findViewById(R.id.url);
        byLine = (TextView) itemView.findViewById(R.id.byline);
    }

    @Override
    public void onClick(View view) {
        if(result.getMultimedia().size() > 0) {
            Intent intent = new Intent(mContext, MultimediaActivity.class);
            intent.putExtra(AppConstants.B_DATA, this.result);
            mContext.startActivity(intent);
        }
        else {
            sendMessage();
        }
    }

    public void invalidate(Result result) {
        this.result = result;
        title.setText(result.getTitle());
        url.setText(result.getUrl());
        abstractText.setText(result.getAbstract());
        byLine.setText(result.getByline());


    }


    private void sendMessage() {
        Intent intent = new Intent(AppConstants.BROADCAST);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }
}
