package com.example.h_mamytov.newflickrphotogallery.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.h_mamytov.newflickrphotogallery.Adapters.FavoriteListAdapter;
import com.example.h_mamytov.newflickrphotogallery.FavoritesPresenter;
import com.example.h_mamytov.newflickrphotogallery.R;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public class FragmentFavorites extends Fragment implements FavoriteListAdapter.DeleteFavItem{

    private FavoriteListAdapter adapter;
    private Handler handler;
    private FavoritesPresenter favoritesPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favorites);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new FavoriteListAdapter();
        handler = new Handler();

        recyclerView.setAdapter(adapter);
        adapter.setDeleteFavItem(this);
        favoritesPresenter = new FavoritesPresenter();
        favoritesPresenter.attachView(this);
        favoritesPresenter.viewIsReady();

        return view;
    }

    public void initData(final List<MyData> myData) {
        adapter.setFavoriteItems(myData);
        adapter.notifyDataSetChanged();
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int deleteFavoriteItem(String secret) {
        return favoritesPresenter.deleteFavItem(secret);
    }
}

