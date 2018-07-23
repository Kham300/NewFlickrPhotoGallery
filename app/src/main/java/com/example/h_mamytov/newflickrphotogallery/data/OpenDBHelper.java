package com.example.h_mamytov.newflickrphotogallery.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import com.example.h_mamytov.newflickrphotogallery.MyData;

public class OpenDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = OpenDBHelper.class.getSimpleName();

    private static OpenDBHelper sInstance;

    public static synchronized OpenDBHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new OpenDBHelper(context.getApplicationContext());
        }
        return sInstance;
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
                + PhotoContract.PhotoEntry._ID +         " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PhotoContract.PhotoEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + PhotoContract.PhotoEntry.COLUMN_URL +  " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertFavoritePhotos(MyData myData, Context context){
        String name = myData.getCaption();
        //TODO url надо переделывать ? _m, _s?
        String url = myData.getUrl();

        OpenDBHelper instance = OpenDBHelper.getInstance(context);

        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PhotoContract.PhotoEntry.COLUMN_NAME, name);
        values.put(PhotoContract.PhotoEntry.COLUMN_URL, url);

        long newRowId = db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, values);

        if (newRowId == -1){
            Toast.makeText(context, "Ошибка при заведении фото", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Фото создано под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }
}
