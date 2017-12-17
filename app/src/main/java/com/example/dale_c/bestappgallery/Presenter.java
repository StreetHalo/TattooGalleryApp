package com.example.dale_c.bestappgallery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.dale_c.bestappgallery.itemCativity.RecyclerViewAdapterItem;
import com.example.dale_c.bestappgallery.itemCativity.ItemActivity;
import com.example.dale_c.bestappgallery.itemCativity.ItemFragment;
import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.json.ParseGson;
import com.example.dale_c.bestappgallery.mainCativity.PhotoGalleryFragment;
import com.example.dale_c.bestappgallery.mainCativity.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */

public class Presenter implements CallbackPresenter {
    private static Presenter presenter;
    private Repository repository;
    private PhotoGalleryFragment photoGalleryFragment;
    private ItemFragment itemFragment;
    private SharedPref sharedPref;
    private int position;
    private int offset;

    private static final String TAG = "Presenter";



    private List<Item> items = new ArrayList<>();
    private String word;


    public static Presenter getInstance() {
        if (presenter == null) {
            presenter = new Presenter();
        }
        return presenter;
    }

    private Presenter(){
        repository = new Repository();
    }

    public void setPhotoGalleryFragment(PhotoGalleryFragment photoGalleryFragment) {
        this.photoGalleryFragment = photoGalleryFragment;
        sharedPref = new SharedPref(photoGalleryFragment.getContext());

    }

    public void setItemFragment(ItemFragment itemFragment) {
        this.itemFragment = itemFragment;
        itemFragment.setAdapter(new RecyclerViewAdapterItem(items), position);

    }

    public void SearchWord(String word){
        this.word = word;
        offset = 0;
        items.clear();
        repository.conRetrofit(word,this, 0);
    }



    @Override
    public void callingbackGson(ParseGson parseGson) {

        items=parseGson.getData().getResult().getItems();

        if(items.size()!=0) photoGalleryFragment.setAdapter(new RecyclerViewAdapter(items,this));

        else            Log.d(TAG, "callingBackFragment: "+"The end");

    }

    @Override
    public void callingBackFragment(Context context, int position) {
        this.position = position;
        Intent intent = new Intent(context,ItemActivity.class);
        context.startActivity(intent);
    }


    public int getPosition() {
        return position;
    }
}

