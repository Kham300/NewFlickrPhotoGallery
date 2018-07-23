package com.example.h_mamytov.newflickrphotogallery;

import android.graphics.drawable.Drawable;

public class MyData {

    private String caption;
    private int id;
    private String url;
    private String farmId;
    private String serverId;
    private String secret;
    private String SIZE_M = "m";
    private String STATIC_URL = ".staticflickr.com/";
    private String FORMAT = ".jpg";
    private String TMP = "http://farm";
    private Drawable drawable;

    public MyData() {
    }

    public MyData(int id, String secret, String server, String farm) {
        this.id = id;
        this.secret = secret;
        this.serverId = server;
        this.farmId = farm;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

}
