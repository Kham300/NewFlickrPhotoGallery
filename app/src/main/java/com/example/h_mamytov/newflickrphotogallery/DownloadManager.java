package com.example.h_mamytov.newflickrphotogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;


public class DownloadManager extends Thread {
    private static final String TAG = "DOWNLOADMANAGER";

    private ImageView imageView;
    private String url;
    private Handler handler;
    private int position;

    DownloadManager(ImageView imageView, String url, int position) {
        this.imageView = imageView;
        this.url = url;
        handler = new Handler();
        this.position = position;
    }


    @Override
    public void run() {
        super.run();
        final Bitmap bitmap = DownloadManager.getBitmapFromURL(url);
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
                Log.i(TAG, "setting image to the position " + position);
            }
        });
    }


    //to download an image
    private static Bitmap getBitmapFromURL(String s) {
        try {
            byte[] bitmapBytes = FlickrFetchr.getUrlBytes(s);
            return BitmapFactory.decodeByteArray(bitmapBytes,0, bitmapBytes.length);
        } catch (IOException ioe){
            Log.e(TAG,"Error downloading image");
        }
        return null;
    }


}
