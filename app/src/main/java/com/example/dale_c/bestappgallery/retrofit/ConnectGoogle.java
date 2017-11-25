package com.example.dale_c.bestappgallery.retrofit;

import android.util.Log;


import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.json.ParseGson;
import com.example.dale_c.bestappgallery.json.Translate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Dale_C on 05.11.2017.
 */

public class ConnectGoogle {

    public static final String URL = "https://api.qwant.com/api/search/images?count=150&offset=";
    public static final String TAG = "ConnectDog";

    private Retrofit retrofit;
    private static APIServiceImages apiServiceImages;
    private static APIServiceTranslate apiServiceTranslate;

    ParseGson parseGson = new ParseGson();
    List<Item> items = new ArrayList<>();
    Translate translate = new Translate();


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ConnectGoogle(String url){

        retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
        if(url.equals("https://api.qwant.com/")){
            apiServiceImages = retrofit.create(APIServiceImages.class);
            System.out.println("qwant!");

        }

        else apiServiceTranslate = retrofit.create(APIServiceTranslate.class);

    }

    public void getImage( String query){

        apiServiceImages.getData(150,1,query).enqueue(new Callback<ParseGson>() {

            @Override
            public void onResponse(Call<ParseGson> call, Response<ParseGson> response) {
               parseGson = response.body();
              items = parseGson.getData().getResult().getItems();
                Log.d(TAG, "onResp: "+items.size());

            }

            @Override
            public void onFailure(Call<ParseGson> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }



}
