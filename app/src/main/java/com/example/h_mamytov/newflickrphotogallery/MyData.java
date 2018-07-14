package com.example.h_mamytov.newflickrphotogallery;

class MyData {
    private String caption;
    private int id;
    private String url;

    public MyData() {
    }

    MyData(String caption, int id, String url) {
        this.caption = caption;
        this.id = id;
        this.url = url;
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
}
