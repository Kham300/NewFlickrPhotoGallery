package com.example.h_mamytov.newflickrphotogallery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.h_mamytov.newflickrphotogallery.Fragments.FragmentFavorites;
import com.example.h_mamytov.newflickrphotogallery.Fragments.FragmentHome;
import com.example.h_mamytov.newflickrphotogallery.Utils.App;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    MainActivityPresenter presenter;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().injectsMainActivity(this);

        showToast(presenter.getData());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment selectedFragment = null;
                        switch (menuItem.getItemId()){
                            case R.id.nav_home:
                                selectedFragment = new FragmentHome();
                                break;
                            case R.id.nav_fav:
                                selectedFragment = new FragmentFavorites();
                                break;
                        }

                        getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();

                        return true;
                    }
        });

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new  FragmentHome());
            transaction.commit();
        }

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

}
