package com.example.h_mamytov.newflickrphotogallery.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h_mamytov.newflickrphotogallery.R;
import com.example.h_mamytov.newflickrphotogallery.Utils.Picaso.Picaso;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MyData> myDataList;
    private Callback callback;

    public CustomAdapter() {
        this.myDataList = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_recent_photos, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final MyData data = myDataList.get(i);

        if (data.isFavorite()){
           viewHolder.button.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            viewHolder.button.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
        viewHolder.imageView.setImageBitmap(null);
        viewHolder.imageView.setTag(data.getUrl());
        ImageView imageView = viewHolder.imageView;
        String url = data.getUrl();
        Picaso.getInstance().download(imageView, url);
    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        private ImageButton button;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            textView = itemView.findViewById(R.id.card_name);
            button = itemView.findViewById(R.id.favorite_item);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyData myData = CustomAdapter.this.myDataList.get(getAdapterPosition());
                    if (!myData.isFavorite()){
                        button.setImageResource(R.drawable.ic_star_black_24dp);
                        callback.insertFavoritePhotos(myData);
                        myData.setFavorite(true);
                    } else {
                        button.setImageResource(R.drawable.ic_star_border_black_24dp);
                        callback.deleteFavoritePhotoBySecret(myData);
                        myDataList.remove(myData);
                    }
                }
            });
        }
    }

    public interface Callback{
        void insertFavoritePhotos(MyData myData);
        void deleteFavoritePhotoBySecret(MyData myData);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setMyDataList(List<MyData> myDataList) {
        this.myDataList = myDataList;
    }
}
