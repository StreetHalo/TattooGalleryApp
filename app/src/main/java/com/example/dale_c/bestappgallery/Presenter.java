package com.example.dale_c.bestappgallery;

import android.util.Log;

import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.json.ParseGson;
import com.example.dale_c.bestappgallery.retrofit.CallbackImg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */

public class Presenter implements CallbackImg {
    private RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getItems());
    private Repository repository;
    private PhotoGalleryFragment photoGalleryFragment;
    public static final String TAG = "Presenter";
    List<Item> items = new ArrayList<>();



    public Presenter(PhotoGalleryFragment photoGalleryFragment){
        Log.d(TAG, "Presenter: ");
        this.photoGalleryFragment = photoGalleryFragment;

        repository = new Repository();
       // photoGalleryFragment.setAdapter(new RecyclerViewAdapter(getItems()));
    }

    public void SearchWord(String word){
        Log.d(TAG, "SearchWord: "+word);
        repository.conRetrofit(word,this);
    }


    @Override
    public void callingBack(ParseGson parseGson) {
        setItems(parseGson.getData().getResult().getItems());
        Log.d(TAG, "callingBack: "+items.size());
       photoGalleryFragment.setAdapter(new RecyclerViewAdapter(parseGson.getData().getResult().getItems()));
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

