package com.catrock.phenologyfriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="phenologyfriend.db";
    private static final int SCHEMA_VERSION=1;
    private static DatabaseHelper singleton=null;

    synchronized static DatabaseHelper getInstance(Context ctxt) {
        if (singleton == null) {
            singleton=new DatabaseHelper(ctxt.getApplicationContext());
        }

        return (singleton);
    }

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS 'specimens'");
        db.execSQL("CREATE TABLE 'specimens' (id INTEGER PRIMARY KEY AUTOINCREMENT, scientific_name TEXT, common_name TEXT, description TEXT);");
        ContentValues args = new ContentValues();
        args.put("scientific_name", "Calinectes sapidis");
        args.put("common_name", "Blue crab");
        args.put("description", "A crab that is blue");
        long rowid= db.insert("specimens", "id", args);
        String res = String.valueOf(rowid);
        Log.d(getClass().getSimpleName(), "Added row # " + res);

    }

    // Upgrading database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Retrieve single specimen detail.
    public SpecimenData getSpecimen(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("specimens", new String[] {
                        "id", "scientific_name", "common_name", "description" },
                "id" + "=?" + id, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SpecimenData specimen = new SpecimenData(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        // return contact
        return specimen;
    }

    // Retrieve all specimens.
    public List<SpecimenData> getAllSpecimens() {
        List<SpecimenData> specimenList = new ArrayList<SpecimenData>();
        // Select All Query
        String selectQuery = "SELECT * FROM specimens ORDER BY scientific_name ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            Log.d(getClass().getSimpleName(), "Retrieved results!");
            do {
                SpecimenData specimen = new SpecimenData();
                specimen.setId(Integer.parseInt(cursor.getString(0)));
                specimen.setScientificName(cursor.getString(1));
                specimen.setCommonName(cursor.getString(2));
                specimen.setDescription(cursor.getString(3));
                // Adding specimenList to list
                specimenList.add(specimen);
            } while (cursor.moveToNext());
        }

        // return contact list
        return specimenList;
    }

}
