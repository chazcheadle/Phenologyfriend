package com.catrock.phenologyfriend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="phenologyfriend.db";
    private static final int SCHEMA_VERSION=1;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db=openOrCreateDatabase("phenologyfriend.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE specimens (id INTEGER PRIMARY KEY, scientific_name TEXT, common_name TEXT);");
    }

    // Upgrading database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
