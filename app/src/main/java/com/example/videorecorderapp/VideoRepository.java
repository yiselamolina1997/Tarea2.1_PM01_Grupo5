package com.example.videorecorderapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class VideoRepository {

    private VideoDatabaseHelper dbHelper;

    public VideoRepository(Context context) {
        dbHelper = new VideoDatabaseHelper(context);
    }

    public void addVideo(String path) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VideoDatabaseHelper.COLUMN_PATH, path);

        db.insert(VideoDatabaseHelper.TABLE_VIDEOS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<String> getAllVideos() {
        List<String> videos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(VideoDatabaseHelper.TABLE_VIDEOS,
                new String[]{VideoDatabaseHelper.COLUMN_PATH},
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                videos.add(cursor.getString(cursor.getColumnIndex(VideoDatabaseHelper.COLUMN_PATH)));
                cursor.moveToNext();
            }
            cursor.close();
        }

        db.close();
        return videos;
    }
}
