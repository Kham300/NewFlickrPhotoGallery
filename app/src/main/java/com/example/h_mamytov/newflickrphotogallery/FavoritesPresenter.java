package com.example.h_mamytov.newflickrphotogallery;

import android.os.Handler;

import com.example.h_mamytov.newflickrphotogallery.Fragments.FragmentFavorites;
import com.example.h_mamytov.newflickrphotogallery.PhotoModel.PhotoModel;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.lang.ref.WeakReference;
import java.util.List;

public class FavoritesPresenter {

    private WeakReference<FragmentFavorites> view;
    private PhotoModel photoModel;
    private Handler handler;

    public FavoritesPresenter() {
        photoModel = new PhotoModel();
        handler = new Handler();
    }

    public void attachView(FragmentFavorites fragmentFavorites){
        view = new WeakReference<>(fragmentFavorites);
    }

    public void viewIsReady() {
        loadUsers();
    }

    private void loadUsers() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               final List<MyData> myData = photoModel.getAllFavItems();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (view != null && myData != null) {
                            final FragmentFavorites fragmentFavorites = view.get();
                            if (fragmentFavorites != null) {
                                fragmentFavorites.initData(myData);
                            }
                        }
                    }
                });
            }
        });
        thread.start();
    }

    public int deleteFavItem(String secret) {
       return photoModel.deletePhoto(secret);
    }
}
