package com.example.h_mamytov.newflickrphotogallery;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        dataList = new ArrayList<>();
        LoadDataFromServer loadDataFromServer = new LoadDataFromServer();
        loadDataFromServer.execute();

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(dataList);
        recyclerView.setAdapter(adapter);

    }
//TODO заменить на Handler
    @SuppressLint("StaticFieldLeak")
    public class LoadDataFromServer extends AsyncTask<Void, Void, Void>  {

            @Override
            protected Void doInBackground(Void... voids) {
                FlickrFetchr.downloadGalleryItems(dataList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        }
}
