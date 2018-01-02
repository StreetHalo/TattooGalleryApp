package com.example.dale_c.bestappgallery.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.dale_c.bestappgallery.Repository;
import com.example.dale_c.bestappgallery.SavingImg;
import com.example.dale_c.bestappgallery.models.SavedRequests;
import com.example.dale_c.bestappgallery.models.SharedPref;
import com.example.dale_c.bestappgallery.models.RegularExpression;
import com.example.dale_c.bestappgallery.view.InterfaceView;
import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.json.ParseGson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */

public class Presenter implements interfacePresenter {
    private static Presenter presenter;
    private Repository repository;
    private SharedPref sharedPref;
    private int offset;
    private List<Item> items = new ArrayList<>();
    private InterfaceView interfaceView;
    private int likePosition;
    private RegularExpression regularExpression;
    private SavedRequests savedRequests;

    private static final String TAG = "Presenter";

    public static Presenter getInstance() {
        if (presenter == null) {
            presenter = new Presenter();
        }
        return presenter;
    }


    private Presenter(){
        regularExpression = new RegularExpression();
        savedRequests = new SavedRequests();
        repository = new Repository();

        File folder = new File("/storage/emulated/0/Pictures"+ File.separator + "Ink");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void setInterfaceView(InterfaceView interfaceView){
        this.interfaceView = interfaceView;
    }




    public void saveLikePosition(int position, Context context, ImageView imageView){
        likePosition=position;
         Picasso.with(context)
                 .load(items.get(position).getMedia())
                   .into(new SavingImg(items.get(position).getId(), imageView ));

    }


    public void foundOldRequest(String request){
        regularExpression.setRequest(request);
    }

    public void SearchWord(String word){
      //  word = word.concat(" tattoo ideas jpg");

        offset = 0;
        items.clear();
        Log.d(TAG, "SearchWord: "+word);
        repository.conRetrofit(word,this, 0);
        savedRequests.addRequest(word);
        regularExpression.setRequests(savedRequests.getRequests());
        regularExpression.removeMaths();
    }



    @Override
    public void callingbackGson(ParseGson parseGson) {

        items=parseGson.getData().getResult().getItems();

        if(items.size()!=0) interfaceView.setGalleryAdapter(items);

    }


    public List<Item> getItems() {
        return items;
    }
}

