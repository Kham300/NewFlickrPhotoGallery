package com.example.h_mamytov.newflickrphotogallery;

import dagger.Component;

@Component(modules = {Modul.class})
public interface AppComponent {
    void injectsMainActivity(MainActivity mainActivity);
}
