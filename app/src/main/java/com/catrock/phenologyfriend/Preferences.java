package com.catrock.phenologyfriend;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.List;

public class Preferences extends PreferenceActivity {

    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
            SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                      String key) {
                    SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    String wuapi_key = SP.getString("credentials_wuapi_key", "NA");
                    Toast.makeText(getBaseContext(), wuapi_key, Toast.LENGTH_LONG).show();                }
    };

    @Override
    public void onBuildHeaders(List<Header> target) {

        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        if (Display.class.getName().equals(fragmentName)) {
            return (true);
        }
        return (false);
    }

    public static class Display extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Load Credentials header
            addPreferencesFromResource(R.xml.preference_credentials);
        }
    }
}