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
        db.execSQL("CREATE TABLE 'specimens' (id INTEGER PRIMARY KEY AUTOINCREMENT, imap_id TEXT, invasive INTEGER, scientific_name TEXT, common_name TEXT, description TEXT);");


    }

    // Upgrading database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Retrieve single specimen detail.
    public SpecimenData getSpecimen(String imap_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("specimens", new String[] {
                        "imap_id", "invasive", "scientific_name", "common_name", "description" },
                "imap_id" + "=?" + imap_id, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SpecimenData specimen = new SpecimenData(
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(2),
                cursor.getString(4),
                cursor.getString(5));
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
            do {
                SpecimenData specimen = new SpecimenData();
                specimen.setImapId(cursor.getString(1));
                specimen.setInvasive(Integer.parseInt(cursor.getString(2)));
                specimen.setScientificName(cursor.getString(3));
                specimen.setCommonName(cursor.getString(4));
                specimen.setDescription(cursor.getString(5));
                // Adding specimenList to list
                specimenList.add(specimen);
            } while (cursor.moveToNext());
        }
        Log.d(getClass().getSimpleName(), "Results: " + specimenList.size());

        // return contact list
        return specimenList;
    }

    // Add Specimen to table.
    public void addSpecimen(SpecimenData specimen) {
        Log.d(getClass().getSimpleName(), "In addSpecimen");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("imap_id", specimen.getImapId());
        args.put("invasive", specimen.getInvasive());
        args.put("scientific_name", specimen.getScientificName());
        args.put("common_name", specimen.getCommonName());
        args.put("description", specimen.getDescription());
        long rowid = db.insert("specimens", "description", args);
        if (rowid >= 0) {
            Log.d(getClass().getSimpleName(), "Added: " + specimen.getCommonName());
        } else {
            Log.d(getClass().getSimpleName(), "Error adding: " + specimen.getCommonName());
        }
    }
}
