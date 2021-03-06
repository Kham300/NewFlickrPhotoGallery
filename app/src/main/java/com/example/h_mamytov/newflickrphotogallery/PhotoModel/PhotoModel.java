package com.example.h_mamytov.newflickrphotogallery.PhotoModel;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.h_mamytov.newflickrphotogallery.Utils.FlickrFetchr;
import com.example.h_mamytov.newflickrphotogallery.data.OpenDBHelper;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.List;

public class PhotoModel {

    private FlickrFetchr flickrFetchr;
    private OpenDBHelper openDBHelper;

    public PhotoModel(FlickrFetchr flickrFetchr) {
        this.flickrFetchr = flickrFetchr;
        openDBHelper = OpenDBHelper.getInstance();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<MyData> downloadGalleryItems() {
        return flickrFetchr.downloadGalleryItems();
    }

    public List<MyData> getAllFavItems(){
        return openDBHelper.getAllFavItems();
    }

    public void insertPhotos(MyData myData) {
        openDBHelper.insertFavoritePhotos(myData);
    }

    public void deletePhoto(MyData myData) {
        openDBHelper.deleteFavoritePhotoBySecret(myData.getSecret());
    }

    public int deletePhoto(String secret) {
        return openDBHelper.deleteFavoritePhotoBySecret(secret);


    }
}
