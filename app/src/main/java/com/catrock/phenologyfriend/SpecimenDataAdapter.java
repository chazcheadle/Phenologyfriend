package com.catrock.phenologyfriend;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class SpecimenDataAdapter extends DatabaseHelper {

    // Retrieve single specimen detail.
    public SpecimenData getSpecimen(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("specimens", new String[] {
                        "id", "scientific_name", "common_name" },
                "id" + "=?" + id, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SpecimenData specimen = new SpecimenData(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));
        // return contact
        return specimen;
    }

    // Retrieve all specimens.
    public List<SpecimenData> getAllSpecimens() {
        List<SpecimenData> specimenList = new ArrayList<SpecimenData>();
        // Select All Query
        String selectQuery = "SELECT * FROM specimens ORDER BY scientificName ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SpecimenData specimen = new SpecimenData();
                specimen.setId(Integer.parseInt(cursor.getString(0)));
                specimen.setScientificName(cursor.getString(1));
                specimen.setCommonName(cursor.getString(2));
                // Adding specimenList to list
                specimenList.add(specimen);
            } while (cursor.moveToNext());
        }

        // return contact list
        return specimenList;
    }

    // Add a specimen.
    public void addSpecimen(SpecimenData specimenData) {

    }

    // Update a specimen.
    public int updateSpecimen(SpecimenData specimenData) {

    }

}
