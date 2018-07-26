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
                + PhotoContract.PhotoEntry._ID +                   " INTEGER PRIMARY KEY AUTOINCREMENT  , "
                + PhotoContract.PhotoEntry.COLUMN_NAME +           " TEXT NOT NULL, "
                + PhotoContract.PhotoEntry.COLUMN_URL +            " TEXT NOT NULL, "
                + PhotoContract.PhotoEntry.COLUMN_IS_FAVORITE +    " INTEGER NOT NULL DEFAULT 0, "
                + PhotoContract.PhotoEntry.COLUMN_SECRET +         " TEXT UNIQUE NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertFavoritePhotos(MyData myData){
        String name = myData.getCaption();
        String url = myData.getUrl();
        String secret = myData.getSecret();
        int isFavorite = myData.isFavorite() ? 1 : 0;

        OpenDBHelper instance = OpenDBHelper.getInstance();

        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME, name);
        values.put(PhotoContract.PhotoEntry.COLUMN_URL, url);
        values.put(PhotoContract.PhotoEntry.COLUMN_IS_FAVORITE, isFavorite);
        values.put(PhotoContract.PhotoEntry.COLUMN_SECRET, secret);

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
                PhotoContract.PhotoEntry.COLUMN_URL,
                PhotoContract.PhotoEntry.COLUMN_IS_FAVORITE,
                PhotoContract.PhotoEntry.COLUMN_SECRET
    };

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
                myData.setFavorite(cursor.getInt(cursor.getColumnIndex(PhotoContract.PhotoEntry.COLUMN_IS_FAVORITE)) == 0);
                myData.setSecret(cursor.getString(cursor.getColumnIndex(PhotoContract.PhotoEntry.COLUMN_SECRET)));
                allFavItems.add(myData);
            }
        }
        return allFavItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<String> getSecretsFromAllFavItems() {
        List<String> secrets = new ArrayList<>();
        SQLiteDatabase db = OpenDBHelper.getInstance().getReadableDatabase();

        String[] projections = {
                PhotoContract.PhotoEntry.COLUMN_SECRET
        };

        try (Cursor cursor = db.query(
                    PhotoContract.PhotoEntry.TABLE_NAME,
                    projections,
                    null,
                    null,
                    null,
                    null,
                    null)){
            while (cursor.moveToNext()){
                secrets.add(cursor.getString(cursor.getColumnIndex(PhotoContract.PhotoEntry.COLUMN_SECRET)));
            }
        }
        return secrets;
    }

    public int deleteFavoritePhotoBySecret(String secret) {
        SQLiteDatabase db = OpenDBHelper.getInstance().getReadableDatabase();
        int delete = db.delete(PhotoContract.PhotoEntry.TABLE_NAME, PhotoContract.PhotoEntry.COLUMN_SECRET + " = " + "'" + secret +"'", null);
        if (delete == -1){
            System.out.println("Error deleting");
        } else {
            System.out.println("deleting");
        }
        return delete;
    }


}
