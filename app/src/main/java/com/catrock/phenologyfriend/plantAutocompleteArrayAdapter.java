package com.catrock.phenologyfriend;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class plantAutocompleteArrayAdapter extends ArrayAdapter<SpecimenData> {

    final String TAG = "plantAutocompleteArrayAdapter.java";

    Context mContext;
    int layoutResourceId;
    List<SpecimenData> specimenData;

    public plantAutocompleteArrayAdapter(Context mContext, int layoutResourceId, List<SpecimenData> specimenData) {

        super(mContext, layoutResourceId, specimenData);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.specimenData = specimenData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try{

            /*
             * The convertView argument is essentially a "ScrapView" as described is Lucas post
             * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
             * It will have a non-null value when ListView is asking you recycle the row layout.
             * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
             */
            if(convertView==null){
                // inflate the layout
                LayoutInflater inflater = ((MainActivity) mContext).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceId, parent, false);
            }

            // object item based on the position
            SpecimenData specimenData = specimenData[position];

            // get the TextView and then set the text (item name) and tag (item ID) values
            TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
            textViewItem.setText(specimenData.objectName);

            // in case you want to add some style, you can do something like:
            textViewItem.setBackgroundColor(Color.CYAN);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;

    }
}