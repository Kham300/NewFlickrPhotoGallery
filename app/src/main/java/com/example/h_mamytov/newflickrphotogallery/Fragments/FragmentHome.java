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

import com.example.h_mamytov.newflickrphotogallery.Adapters.CustomAdapter;
import com.example.h_mamytov.newflickrphotogallery.HomePresenter;
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
        HomePresenter homePresenter = new HomePresenter();
        homePresenter.attachView(this);
        homePresenter.viewIsReady();
        return view;
    }

    public void initData(final List<MyData> myData) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.setMyDataList(myData);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
