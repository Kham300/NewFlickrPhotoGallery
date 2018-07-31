package com.example.h_mamytov.newflickrphotogallery.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.h_mamytov.newflickrphotogallery.Adapters.CustomAdapter;
import com.example.h_mamytov.newflickrphotogallery.HomePhotoView;
import com.example.h_mamytov.newflickrphotogallery.HomePresenter;
import com.example.h_mamytov.newflickrphotogallery.R;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public class FragmentHome extends MvpAppCompatFragment implements CustomAdapter.Callback, HomePhotoView{

    @InjectPresenter
    public HomePresenter homePresenter;

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
        adapter.setCallback(this);

        handler = new Handler();

        return view;
    }

    @Override
    public void show(final List<MyData> myData) {
        adapter.setMyDataList(myData);
        adapter.notifyDataSetChanged();
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertFavoritePhotos(MyData myData) {
        homePresenter.insertFavoritePhotos(myData);
    }

    @Override
    public void deleteFavoritePhotoBySecret(MyData myData) {
        homePresenter.deleteFavoritePhoto(myData);
    }
}
