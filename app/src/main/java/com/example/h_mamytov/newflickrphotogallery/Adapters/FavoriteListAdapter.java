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
import com.example.h_mamytov.newflickrphotogallery.Utils.BlockingLifoQueue;
import com.example.h_mamytov.newflickrphotogallery.Utils.DownloadManager;
import com.example.h_mamytov.newflickrphotogallery.data.OpenDBHelper;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {

    private List<MyData> favoriteItems;
    private ThreadPoolExecutor threadPoolExecutor ;

    public FavoriteListAdapter() {
        favoriteItems = new ArrayList<>();
        threadPoolExecutor = new ThreadPoolExecutor(3,3,1, TimeUnit.MINUTES, new BlockingLifoQueue<Runnable>());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MyData myData = favoriteItems.get(i);
        viewHolder.imageView.setImageBitmap(null);
        viewHolder.imageView.setTag(myData.getUrl());
        threadPoolExecutor.execute(new DownloadManager(viewHolder.imageView, myData.getUrl(), i));

    }

    @Override
    public int getItemCount() {
        return favoriteItems.size();
    }

    public void setFavoriteItems(List<MyData> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        private ImageButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.favorite_card_image);
            textView = itemView.findViewById(R.id.fav_card_name);
            button = itemView.findViewById(R.id.in_favorite_item_btn);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenDBHelper instance = OpenDBHelper.getInstance();
                    MyData myData = favoriteItems.get(getAdapterPosition());
                    int i = instance.deleteFavoritePhotoBySecret(myData.getSecret());
                    if (i != -1){
                        favoriteItems.remove(myData);
                        notifyItemRemoved(getAdapterPosition());
                    }
                }
            });
        }
    }
}
