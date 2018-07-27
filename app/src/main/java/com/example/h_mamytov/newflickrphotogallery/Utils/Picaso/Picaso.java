package com.example.h_mamytov.newflickrphotogallery.Utils.Picaso;

import android.widget.ImageView;

import com.example.h_mamytov.newflickrphotogallery.Utils.BlockingLifoQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Picaso {
static Picaso picaso;
    ThreadPoolExecutor   threadPoolExecutor;
    public Picaso() {

        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new BlockingLifoQueue<Runnable>());
    }

  public   static Picaso getInstance(){


        if(picaso==null){


            picaso =new Picaso();
        }
        return picaso;


    }

    public   void download(ImageView imageView, String url) {
        threadPoolExecutor.execute(new  DownloadManager(imageView, url));
    }


}
