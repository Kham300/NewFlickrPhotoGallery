package com.example.h_mamytov.newflickrphotogallery.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h_mamytov.newflickrphotogallery.Adapters.FavoriteListAdapter;
import com.example.h_mamytov.newflickrphotogallery.R;
import com.example.h_mamytov.newflickrphotogallery.data.OpenDBHelper;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public class FragmentFavorites extends Fragment {
    private FavoriteListAdapter adapter;
    private Handler handler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favorites);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        initData();

        adapter = new FavoriteListAdapter();
        recyclerView.setAdapter(adapter);
        handler = new Handler();

        return view;
    }

    public void initData() {
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                List<MyData> myData = OpenDBHelper.getInstance().getAllFavItems();
                if (myData != null) {
                    adapter.setFavoriteItems(myData);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();
    }
}