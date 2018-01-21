package com.example.dale_c.bestappgallery.retrofit;

import android.util.Log;


import com.example.dale_c.bestappgallery.presenter.InterfacePresenter;
import com.example.dale_c.bestappgallery.json.ParseGson;
import com.example.dale_c.bestappgallery.json.Translate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Dale_C on 05.11.2017.
 */

public class ConnectGoogle {

    public static final String TAG = "ConnectDog";

    private Retrofit retrofit;
    private static APIServiceImages apiServiceImages;
    private static APIServiceTranslate apiServiceTranslate;

    ParseGson parseGson = new ParseGson();
    Translate translate = new Translate();


    private InterfacePresenter InterfacePresenter;


    public void registerCallBack(InterfacePresenter InterfacePresenter){
        this.InterfacePresenter = InterfacePresenter;
    }


    public ConnectGoogle(String url){

        retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
        if(url.equals("https://api.qwant.com/")){
            apiServiceImages = retrofit.create(APIServiceImages.class);

        }
        else apiServiceTranslate = retrofit.create(APIServiceTranslate.class);

    }

    public void getImage( String query, int offset){
        Log.d(TAG, "offset: "+offset);
        apiServiceImages.getData(120,offset,query).enqueue(new Callback<ParseGson>() {

            @Override
            public void onResponse(Call<ParseGson> call, Response<ParseGson> response) {
               parseGson = response.body();
                InterfacePresenter.callingbackGson(parseGson);

            }

            @Override
            public void onFailure(Call<ParseGson> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }



}
