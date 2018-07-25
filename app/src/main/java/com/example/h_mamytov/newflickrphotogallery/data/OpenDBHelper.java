package com.example.h_mamytov.newflickrphotogallery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.example.h_mamytov.newflickrphotogallery.entity.MyData;

import java.util.ArrayList;
import java.util.List;

public class OpenDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = OpenDBHelper.class.getSimpleName();
    private static OpenDBHelper sInstance;

    public static synchronized OpenDBHelper getInstance(){
        if (sInstance == null){
    throw  new RuntimeException("OpenDBHelper не был заиничин");
        }
        return sInstance;
    }


    public static void init(Context context){
        sInstance = new OpenDBHelper(context);
    }


    //имя файла базы данных
    private static final String DATABASE_NAME = "photos.db";
    private static final int DATABASE_VERSION = 1;

    private OpenDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + PhotoContract.PhotoEntry.TABLE_NAME + " ("
                + PhotoContract.PhotoEntry._ID +         " INTEGER PRIMARY KEY AUTOINCREMENT  , "
                + PhotoContract.PhotoEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + PhotoContract.PhotoEntry.COLUMN_URL +  " TEXT UNIQUE NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertFavoritePhotos(MyData myData){
        String name = myData.getCaption();
        //TODO url надо переделывать _m, _s?
        String url = myData.getUrl();

        OpenDBHelper instance = OpenDBHelper.getInstance();

        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME, name);
        values.put(PhotoContract.PhotoEntry.COLUMN_URL, url);

        long newRowId = db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, values);

        if (newRowId == -1){
            System.out.println("Error saving");
        } else {
            System.out.println("saving" + newRowId);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<MyData> getAllFavItems() {
        List<MyData> allFavItems = new ArrayList<>();
        SQLiteDatabase db = OpenDBHelper.getInstance().getReadableDatabase();

        String[] projection = {
                PhotoContract.PhotoEntry._ID,
                PhotoContract.PhotoEntry.COLUMN_NAME,
                PhotoContract.PhotoEntry.COLUMN_URL};

        //делаем запрос
        try (Cursor cursor = db.query(
                PhotoContract.PhotoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null)) {

            while (cursor.moveToNext()) {
                MyData myData = new MyData();
                myData.setId(cursor.getInt(cursor.getColumnIndex(PhotoContract.PhotoEntry._ID)));
                myData.setCaption(cursor.getString(cursor.getColumnIndex(PhotoContract.PhotoEntry.COLUMN_NAME)));
                myData.setUrl(cursor.getString(cursor.getColumnIndex(PhotoContract.PhotoEntry.COLUMN_URL)));
                allFavItems.add(myData);
            }
        }

        return allFavItems;
    }

    public int deleteFavoritePhotoById(int id) {
        // sqLiteDatabase.delete(MYDATABASE_TABLE, KEY_ID+"="+id, null);
        SQLiteDatabase db = OpenDBHelper.getInstance().getReadableDatabase();
        int delete = db.delete(PhotoContract.PhotoEntry.TABLE_NAME, PhotoContract.PhotoEntry._ID + "=" + id, null);
        if (delete == -1){
            System.out.println("Error deleting");
        } else {
            System.out.println("deleting");
        }
        return delete;
    }
}
