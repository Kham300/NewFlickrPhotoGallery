package com.example.h_mamytov.newflickrphotogallery;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import com.example.h_mamytov.newflickrphotogallery.Fragments.FragmentFavorites;
import com.example.h_mamytov.newflickrphotogallery.data.OpenDBHelper;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public class FavoritesPresenter {

    private FragmentFavorites view;

    Handler handler;

    public FavoritesPresenter() {
        //UI
        handler=new Handler();
    }

    public void attachView(FragmentFavorites fragmentFavorites){
        view = fragmentFavorites;
    }

    public void viewIsReady() {
        loadUsers();
    }

    public void loadUsers() {
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                List<MyData> myData = OpenDBHelper.getInstance().getAllFavItems();
                view.initData(myData);
            }
        });
        thread.start();
    }

}
