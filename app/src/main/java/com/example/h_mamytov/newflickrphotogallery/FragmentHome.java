package com.example.h_mamytov.newflickrphotogallery;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

public class FragmentHome extends Fragment {

    private CustomAdapter adapter;
    private Handler handler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);


        initData();

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        handler = new Handler();

        return view;
    }

    public void initData(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<MyData> myData = FlickrFetchr.downloadGalleryItems();
                adapter.setMyData(myData);

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
