package com.example.dale_c.bestappgallery.models;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mihailov on 28.12.2017.
 */

public class RegularExpression {

    public static final String TAG = "RegularExpression";

    private Pattern pattern;
    private Matcher matcher;
    private Set<String> oldRequests;
    private Set<String> maths;
    private boolean found;

    public RegularExpression() {
        maths = new HashSet<String>();
        oldRequests = new HashSet<String>();
    }


    public void setRequest(String request) {
        pattern = Pattern.compile(request);


        for (String s : oldRequests) {
            matcher = pattern.matcher(s);
            found = matcher.find();
            if (found) maths.add(s);
            }

        if (found) {
            for (String a : maths) {
                Log.d(TAG, "setPattern: " + a);
            }
        }
    }

    public void setRequests(Set<String> oldRequests) {
        this.oldRequests = oldRequests;
    }

    public void removeMaths(){

        maths.clear();
    }

}

