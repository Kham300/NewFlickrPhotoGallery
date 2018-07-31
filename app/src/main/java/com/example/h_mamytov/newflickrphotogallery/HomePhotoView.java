package com.example.h_mamytov.newflickrphotogallery;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

@StateStrategyType(AddToEndStrategy.class)
public interface HomePhotoView extends MvpView {

    void show(final List<MyData> myData);
}
