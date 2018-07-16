package com.example.h_mamytov.newflickrphotogallery;

import android.os.Handler;

public class Presenter {



    Handler handler;
    public Presenter() {
        //UI

        handler=new Handler();
    }

    public void getData(){

        new Thread(new Runnable() {
            @Override
            public void run() {



                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });



            }
        }).start();



    }
}
