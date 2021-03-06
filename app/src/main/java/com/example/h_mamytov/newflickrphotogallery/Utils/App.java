package com.example.h_mamytov.newflickrphotogallery.Utils;

import android.app.Application;

import com.example.h_mamytov.newflickrphotogallery.AppComponent;
import com.example.h_mamytov.newflickrphotogallery.DaggerAppComponent;
import com.example.h_mamytov.newflickrphotogallery.data.OpenDBHelper;
import com.facebook.stetho.Stetho;

public class App extends Application {

    private static AppComponent modelComponent2;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);

        OpenDBHelper.init(this);

        //Dagger
        modelComponent2 = DaggerAppComponent.builder()
                .build();

    }

    public static AppComponent getComponent() {
        return modelComponent2;
    }
}
