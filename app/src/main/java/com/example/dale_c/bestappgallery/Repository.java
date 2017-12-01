package com.example.dale_c.bestappgallery;

import android.util.Log;

import com.example.dale_c.bestappgallery.retrofit.CallbackImg;
import com.example.dale_c.bestappgallery.retrofit.ConnectGoogle;

/**
 * Created by Dale_C on 25.11.2017.
 */

public class Repository  {

    public static final String TAG = "Repository";
    public static final String URL_TRANSLATE = "https://translate.yandex.net/";
    public static final String URL_IMAGES = "https://api.qwant.com/";
    ConnectGoogle conImage;
    ConnectGoogle conTranslate ;




    public  Repository(){
         conImage = new ConnectGoogle(URL_IMAGES);
         conTranslate = new ConnectGoogle(URL_TRANSLATE);

    }

    public void conRetrofit(String word, CallbackImg callbackImg){
        Log.d(TAG, "conRetrofit: "+word);
        conImage.registerCallBack(callbackImg);
        conImage.getImage(word);

    }


}
