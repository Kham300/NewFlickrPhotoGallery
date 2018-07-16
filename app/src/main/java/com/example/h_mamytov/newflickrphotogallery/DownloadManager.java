package com.example.h_mamytov.newflickrphotogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;


public class DownloadManager {

    private static final String TAG = "Download Manager";

    public DownloadManager() {
    }

    public static   Drawable getCurrentItemImage(String url){
        Bitmap bitmap = getBitmapFromURL(url);
        //Convert bitmap to drawable
        return new BitmapDrawable(bitmap);
    }

    //to download an image
    public static Bitmap getBitmapFromURL(String s) {
        try {
            byte[] bitmapBytes =FlickrFetchr.getUrlBytes(s);
            return BitmapFactory.decodeByteArray(bitmapBytes,0, bitmapBytes.length);
        } catch (IOException ioe){
            Log.e(TAG,"Error downloading image");
        }
        return null;
    }



//        URL url = new URL(s);
//        HttpURLConnection connection = (HttpURLConnection) url
//                .openConnection();
//        connection.setDoInput(true);
//        connection.connect();
//        InputStream input = connection.getInputStream();
//        return BitmapFactory.decodeStream(input);
//    } catch (IOException e) {
//        e.printStackTrace();
//        return null;
//    }

}
