package com.example.h_mamytov.newflickrphotogallery.data;

import android.provider.BaseColumns;

public final class PhotoContract {

    private PhotoContract(){
    }

    public static final class PhotoEntry implements BaseColumns{
        public static final String TABLE_NAME = "favorite_photos";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_SECRET = "secret";
        public static final String COLUMN_IS_FAVORITE = "favorite";
    }

}
