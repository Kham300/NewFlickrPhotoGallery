package com.example.h_mamytov.newflickrphotogallery;

import com.example.h_mamytov.newflickrphotogallery.Utils.FlickrFetchr;

import dagger.Module;
import dagger.Provides;

@Module
class Modul {

    @Provides
    MainActivityPresenter providePresenter(Interactor interactor){
        return new MainActivityPresenter(interactor);
    }

    @Provides
    Interactor provideInteractor(FlickrFetchr flickrFetchr){
        return new Interactor(flickrFetchr);
    }

    @Provides
    FlickrFetchr provideFlick(){
        return new FlickrFetchr();
    }
}
