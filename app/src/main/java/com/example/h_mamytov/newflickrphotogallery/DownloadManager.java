package com.example.h_mamytov.newflickrphotogallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class DownloadManager extends Thread {
    private static final String TAG = "DOWNLOADMANAGER";

    private ImageView imageView;
    private String url;
    private Handler handler;
    private int position;
    private Context context;
    private Bitmap bitmap;
    private File file;

    DownloadManager(ImageView imageView, String url, int position) {
        this.imageView = imageView;
        this.url = url;
        handler = new Handler();
        this.position = position;
        context = imageView.getContext();
        file = new File(context.getCacheDir(), createFileName(url) );
    }


    @Override
    public void run() {
        super.run();
        if (file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            Log.i(TAG, "getting image from cache " + file.getName());
            kokok();
        } else {
            String url_q = url.substring(0, url.length() - 5);
            url_q = url_q + "m.jpg";
            bitmap = DownloadManager.getBitmapFromURL(url_q);
            kokok();
            if (bitmap != null) {
                savePicture(bitmap);
            }
        }
    }

    private void kokok() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    System.out.println("didn't match");
                }
                Log.i(TAG, "setting image to the position " + position);
            }
        });
    }


    //to download an image
    private static Bitmap getBitmapFromURL(String url) {
        try {
            byte[] bitmapBytes = FlickrFetchr.getUrlBytes(url);
            return BitmapFactory.decodeByteArray(bitmapBytes,0, bitmapBytes.length);
        } catch (IOException ioe){
            Log.e(TAG,"Error downloading image");
        }
        return null;
    }

    private void savePicture(Bitmap bitmap){
        OutputStream out;
//        String fileName = createFileName(url);
//        try {
//            File file = new File(context.getCacheDir(), fileName);
//
//            if (!file.exists()) {
        try {
//                file.createNewFile();
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);
                out.flush();
                out.close();

                Log.i(TAG, "file saved in folder " + context.getCacheDir() + " name " + file.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createFileName(String url){
        String result = url.substring(8, url.length() - 6);
        result = result.replace("/", "");
        result = result.replace(".", "");
        return result;
    }

}
