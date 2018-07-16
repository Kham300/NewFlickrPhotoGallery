package com.example.h_mamytov.newflickrphotogallery;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MyData> myData;

    public CustomAdapter( List<MyData> dataList) {
        this.myData = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        MyData data = myData.get(i);
        viewHolder.textView.setText(data.getCaption());
//        Bitmap bitmap = FlickrFetchr.getBitmapFromURL();
//        //Convert bitmap to drawable
//        Drawable drawable = new BitmapDrawable(bitmap);
//        viewHolder.bindDrawable(drawable);
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
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }
        public void bindDrawable(Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }
    }
}
