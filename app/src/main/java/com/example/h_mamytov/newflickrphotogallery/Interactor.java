package com.example.h_mamytov.newflickrphotogallery;

import com.example.h_mamytov.newflickrphotogallery.Utils.FlickrFetchr;

public class Interactor {

    FlickrFetchr flickrFetchr;


    public Interactor(FlickrFetchr flickrFetchr) {
        this.flickrFetchr = flickrFetchr;
    }

    public String loadData() {
        return flickrFetchr.showFlickr();
    }
}
