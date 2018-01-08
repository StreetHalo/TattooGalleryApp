package com.example.dale_c.bestappgallery.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.dale_c.bestappgallery.models.FolderManager;
import com.example.dale_c.bestappgallery.models.Repository;
import com.example.dale_c.bestappgallery.models.SavingPic;
import com.example.dale_c.bestappgallery.models.SharedPref;
import com.example.dale_c.bestappgallery.view.InterfaceView;
import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.json.ParseGson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dale_C on 25.11.2017.
 */

public class Presenter implements interfacePresenter {
    private static Presenter presenter;
    private Repository repository;
    private SharedPref sharedPref;
    private int offset;
    private List<Item> items = new ArrayList<>();
    private FolderManager folderManager;
    private InterfaceView interfaceView;
    private String codeWord = "tattoo";

    private static final String TAG = "Presenter";

    public static Presenter getInstance(Context context) {
        if (presenter == null) {
            presenter = new Presenter(context);
        }
        return presenter;
    }


    private Presenter(Context context){
        repository = new Repository();
        folderManager = new FolderManager();
        sharedPref = new SharedPref(context);
    }

    public void setInterfaceView(InterfaceView interfaceView){
        this.interfaceView = interfaceView;
    }

    public void delPic(int position){

        folderManager.delSavedPic(position);
    }

    public void saveLikePosition(int position, Context context, ImageView imageView){
                items.get(position).setLiked();
                Picasso.with(context)
                        .load(items.get(position).getMedia())
                        .into(new SavingPic(items.get(position).getId(),imageView));
            }





    public void SearchWord(String word){

        word = word.concat(codeWord);
        offset = 0;
        items.clear();
        Log.d(TAG, "SearchWord: "+word);
        repository.conRetrofit(word,this, 0);
    }



    @Override
    public void callingbackGson(ParseGson parseGson) {

        items=parseGson.getData().getResult().getItems();

        if(items.size()!=0) interfaceView.uploadGalleryAdapter();

    }


    public List<Item> getItems() {
        return items;
    }

    public List<String> getSavedPics(){return folderManager.getSavedPics();}

    public void updateSavedPic(){
        folderManager.updateSavedPics();
    }

    public void setRequestsfrmSharedPref(Set<String> requests){
        sharedPref.setRequests(requests);
    }

    public Set<String> getRequestsfrmSharedPref(){
        return  sharedPref.getRequests();
    }

}

