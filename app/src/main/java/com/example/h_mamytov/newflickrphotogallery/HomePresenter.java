package com.example.h_mamytov.newflickrphotogallery;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import com.example.h_mamytov.newflickrphotogallery.Fragments.FragmentHome;
import com.example.h_mamytov.newflickrphotogallery.Utils.FlickrFetchr;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public class HomePresenter {

    private FragmentHome view;

    Handler handler;

    public HomePresenter() {
        //UI
        handler=new Handler();
    }

    public void attachView(FragmentHome fragmentHome){
        view = fragmentHome;
    }

    public void viewIsReady() {
        loadUsers();
    }

    private void loadUsers() {
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                List<MyData> myData = FlickrFetchr.downloadGalleryItems();
                view.initData(myData);
            }
        });
        thread.start();
    }


}
