package com.example.dale_c.bestappgallery.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by Dale_C on 12.11.2017.
 */

public class SharedPref {
    private static final String QUERIES = "Queries";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences settings;

    private Set<String> requests;
    private Context context;

    public SharedPref(Context context){
        requests = new HashSet<>();
        settings = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public Set<String> getRequests(){

        try {
            requests.addAll(settings.getStringSet(QUERIES,null)) ;

        }
        catch (Exception e){
            requests.add("kek");
        }

        return requests;
    }

    public void setRequests(Set<String> queries){
        editor = sharedPreferences.edit();
        editor.putStringSet(QUERIES,queries);
        editor.apply();
    }


}
