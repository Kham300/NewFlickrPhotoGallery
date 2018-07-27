package com.example.h_mamytov.newflickrphotogallery;

import com.arellomobile.mvp.MvpView;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public interface HomePhotoView extends MvpView {
    void show(final List<MyData> myData);
}
