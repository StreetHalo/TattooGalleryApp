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

    public ConnectGoogle(String url){

        retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

        if(url.equals("https://api.qwant.com/"))
        apiServiceImages = retrofit.create(APIServiceImages.class);
        else apiServiceTranslate = retrofit.create(APIServiceTranslate.class);
    }

    public void getImage(){

        apiServiceImages.getData(150,1,"cats").enqueue(new Callback<ParseGson>() {
            @Override
            public void onResponse(Call<ParseGson> call, Response<ParseGson> response) {
               parseGson = response.body();
                items = parseGson.getData().getResult().getItems();
                Log.d(TAG, "onResp: "+items.size());
            }

            @Override
            public void onFailure(Call<ParseGson> call, Throwable t) {

            }
        });
    }


    public void getTranslate(){
        apiServiceTranslate.getData("cats","en-ru").enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {
                translate = response.body();
                Log.d(TAG, "onResponse: "+translate.getText());
            }

            @Override
            public void onFailure(Call<Translate> call, Throwable t) {

            }
        });
    }
}
