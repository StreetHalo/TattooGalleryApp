package com.example.dale_c.bestappgallery.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dale_C on 12.11.2017.
 */

public class SharedPref {
    private static final String QUERIES = "Queries";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPref(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(QUERIES,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getQueries(){
      String a =  sharedPreferences.getString(QUERIES,"");
      return a;
    }

    public void setQueries(String queries){
        editor.putString(QUERIES,queries);
        editor.commit();
    }


}
