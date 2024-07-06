package com.example.videorecorderapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VideoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "videos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_VIDEOS = "videos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PATH = "path";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_VIDEOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PATH + " TEXT NOT NULL);";

    public VideoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEOS);
        onCreate(db);
    }
}


