package com.example.h_mamytov.newflickrphotogallery;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> dataList;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        dataList = new ArrayList<>();

        initData();

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(dataList);
        recyclerView.setAdapter(adapter);

        handler = new Handler(){
            public void handleMessage(Message msg){
                if (msg.what !=0) {
                    adapter.notifyDataSetChanged();
                    System.out.println("received images: = " + msg.what);
                }
            }
        };

    }

    public void initData(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FlickrFetchr.downloadGalleryItems(dataList);
                handler.sendEmptyMessage(dataList.size());
            }
        });
        thread.start();
    }

//TODO заменить на Handler
    @SuppressLint("StaticFieldLeak")
    public class LoadDataFromServer extends AsyncTask<Void, Void, Void>  {

            @Override
            protected Void doInBackground(Void... voids) {

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        }
}
