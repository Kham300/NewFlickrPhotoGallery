package com.example.h_mamytov.newflickrphotogallery;

import com.example.h_mamytov.newflickrphotogallery.Fragments.FragmentHome;

import dagger.Component;

@Component(modules = {Modul.class, FragmentsModule.class})
public interface AppComponent {
    void injectsMainActivity(MainActivity mainActivity);
    void injectsHomeFragment(FragmentHome fragmentHome);
}
