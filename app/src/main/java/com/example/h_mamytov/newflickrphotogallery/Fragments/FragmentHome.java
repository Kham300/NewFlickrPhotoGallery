package com.example.h_mamytov.newflickrphotogallery.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h_mamytov.newflickrphotogallery.Adapters.CustomAdapter;
import com.example.h_mamytov.newflickrphotogallery.Utils.FlickrFetchr;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;
import com.example.h_mamytov.newflickrphotogallery.R;

import java.util.List;

public class FragmentHome extends Fragment {

    private CustomAdapter adapter;
    private Handler handler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);



        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        handler = new Handler();

        initData();

        return view;
    }

    public void initData(){
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                List<MyData> myData = FlickrFetchr.downloadGalleryItems();
                adapter.setMyDataList(myData);

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
