package com.example.h_mamytov.newflickrphotogallery;

import com.example.h_mamytov.newflickrphotogallery.PhotoModel.PhotoModel;
import com.example.h_mamytov.newflickrphotogallery.Utils.FlickrFetchr;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentsModule {

    @Provides
    HomePresenter provideHomePresenter(PhotoModel photoModel){
        return new HomePresenter(photoModel);
    }

    @Provides
    PhotoModel providesPhotoModel(FlickrFetchr flickrFetchr){
        return new PhotoModel(flickrFetchr);
    }
}
