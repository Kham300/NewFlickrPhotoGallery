package com.example.h_mamytov.newflickrphotogallery;

import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.h_mamytov.newflickrphotogallery.PhotoModel.PhotoModel;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomePhotoView> {

    private PhotoModel photoModel;
    private Handler handler;

    public HomePresenter() {
        photoModel = new PhotoModel();
        handler = new Handler();
        getData();
    }

    public void getData() {
        loadUsers();
    }

    private void loadUsers() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final List<MyData> myData = photoModel.downloadGalleryItems();
                if (!myData.isEmpty()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            getViewState().show(myData);
                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void insertFavoritePhotos(MyData myData) {
        photoModel.insertPhotos(myData);
    }

    public void deleteFavoritePhoto(MyData myData) {
        photoModel.deletePhoto(myData);
    }
}
