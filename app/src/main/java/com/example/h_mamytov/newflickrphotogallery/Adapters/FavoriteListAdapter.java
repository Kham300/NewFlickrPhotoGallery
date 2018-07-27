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

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {
    private DeleteFavItem deleteFavItem;

    private List<MyData> favoriteItems;

    public FavoriteListAdapter() {
        favoriteItems = new ArrayList<>();
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
        String url = myData.getUrl();
        ImageView imageView = viewHolder.imageView;
        viewHolder.imageView.setImageBitmap(null);
        viewHolder.imageView.setTag(url);
        Picaso.getInstance().download(imageView, url);
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
                    MyData myData = favoriteItems.get(getAdapterPosition());
                    int i = deleteFavItem.deleteFavoriteItem(myData.getSecret());
                    if (i != -1){
                        favoriteItems.remove(myData);
                        notifyItemRemoved(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface DeleteFavItem{
        int deleteFavoriteItem(String secret);
    }

    public void setDeleteFavItem(DeleteFavItem deleteFavItem) {
        this.deleteFavItem = deleteFavItem;
    }
}
