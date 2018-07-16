package com.example.h_mamytov.newflickrphotogallery;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MyData> myData;
    private Handler handler;

    public CustomAdapter() {
        this.myData = new ArrayList<>();
        handler = new Handler();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final MyData data = myData.get(i);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Drawable drawable = DownloadManager.getCurrentItemImage(data.getUrl());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.bindDrawable(drawable);
                    }
                });
            }
        });
        thread.start();
    }


    @Override
    public int getItemCount() {
        return myData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            textView = itemView.findViewById(R.id.text);
        }
        public void bindDrawable(Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void setMyData(List<MyData> myData) {
        this.myData = myData;
    }
}
