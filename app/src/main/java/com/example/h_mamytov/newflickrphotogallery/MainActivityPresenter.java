package com.example.h_mamytov.newflickrphotogallery;

public class MainActivityPresenter {

    Interactor interactor;

    public MainActivityPresenter(Interactor interactor) {
        this.interactor = interactor;
    }

    public String  getData(){
        return interactor.loadData();
    }
}
