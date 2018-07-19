package com.example.h_mamytov.newflickrphotogallery;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        initData();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        handler = new Handler();
    }

    public void initData(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<MyData> myData = FlickrFetchr.downloadGalleryItems();
                adapter.setMyData(myData);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();
    }

}
