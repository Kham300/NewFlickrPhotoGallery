package com.example.h_mamytov.newflickrphotogallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MyData> myData;
    private ThreadPoolExecutor threadPoolExecutor ;

    private Context context;

    public CustomAdapter() {
        this.myData = new ArrayList<>();
        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new BlockingLifoQueue<Runnable>());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final MyData data = myData.get(i);

        viewHolder.imageView.setImageBitmap(null);
        viewHolder.imageView.setTag(data.getUrl());
        threadPoolExecutor.execute(new DownloadManager(viewHolder.imageView, data.getUrl(), i));
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

    }

    public void setMyData(List<MyData> myData) {
        this.myData = myData;
    }
}
