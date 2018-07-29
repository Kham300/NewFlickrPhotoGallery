package com.example.h_mamytov.newflickrphotogallery;

import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.h_mamytov.newflickrphotogallery.PhotoModel.PhotoModel;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

@InjectViewState
public class FavoritesPresenter extends MvpPresenter<FavoritePhotoView> {

    private PhotoModel photoModel;
    private Handler handler;

    public FavoritesPresenter() {
        photoModel = new PhotoModel();
        handler = new Handler();
    }

    public void getData() {
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
                        getViewState().show(myData);
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
