package com.example.h_mamytov.newflickrphotogallery.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h_mamytov.newflickrphotogallery.Utils.BlockingLifoQueue;
import com.example.h_mamytov.newflickrphotogallery.Utils.DownloadManager;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;
import com.example.h_mamytov.newflickrphotogallery.R;
import com.example.h_mamytov.newflickrphotogallery.data.OpenDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MyData> myDataList;
    private ThreadPoolExecutor threadPoolExecutor ;

    public CustomAdapter() {
        this.myDataList = new ArrayList<>();
        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new BlockingLifoQueue<Runnable>());
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
        threadPoolExecutor.execute(new DownloadManager(viewHolder.imageView, data.getUrl(), i));
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
                        OpenDBHelper instance = OpenDBHelper.getInstance();
                        instance.insertFavoritePhotos(myData);
                        myData.setFavorite(true);
                    } else {
                        button.setImageResource(R.drawable.ic_star_border_black_24dp);
                        OpenDBHelper instance = OpenDBHelper.getInstance();
                        instance.deleteFavoritePhotoBySecret(myData.getSecret());
                        myDataList.remove(myData);
                    }
                }
            });

        }

    }

    public void setMyDataList(List<MyData> myDataList) {
        this.myDataList = myDataList;
    }
}
