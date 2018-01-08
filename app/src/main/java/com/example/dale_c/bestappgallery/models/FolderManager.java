package com.example.dale_c.bestappgallery.models;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.dale_c.bestappgallery.view.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dale_C on 03.01.2018.
 */

public class FolderManager {
    private static final String path = "/storage/emulated/0/Pictures/Ink/";
    private File folder;
    private File delPic;
    private static final String TAG = "SavedPics";
    private   List<String> savedPics;
    private Boolean isDel;

    public FolderManager()  {
        savedPics = new ArrayList<>();
        folder = new File("/storage/emulated/0/Pictures"+ File.separator + "Ink");
        if (!folder.exists()) {
            Boolean a =folder.mkdirs();
        }
        updateSavedPics();
    }

    public void updateSavedPics(){

    savedPics.clear();
    for(File file:folder.listFiles()){
            savedPics.add(file.getName());
        }
    }

    public List<String> getSavedPics() {
        return savedPics;
    }

    public void delSavedPic(int id){
        Log.d(TAG, "delSavedPic: "+savedPics.get(id));
        Log.d(TAG, "delSavedPic: "+id);
        delPic = new File(path+savedPics.get(id)) ;
        isDel = delPic.delete();
        Log.d(TAG, "delSavedPic: "+"DEL"+isDel);
    }
}
