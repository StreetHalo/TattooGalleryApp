package com.example.dale_c.bestappgallery.models;

import com.example.dale_c.bestappgallery.presenter.InterfacePresenter;
import com.example.dale_c.bestappgallery.view.mainActivity.InterfaceView;

import java.io.File;
import java.util.ArrayList;
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
    private InterfacePresenter interfacePresenter;


    public FolderManager(InterfacePresenter interfacePresenter)  {
        this.interfacePresenter = interfacePresenter;
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

        delPic = new File(path+savedPics.get(id)) ;
        isDel = delPic.delete();
    }
}
