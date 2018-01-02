package com.example.dale_c.bestappgallery;

import android.util.Log;

import com.example.dale_c.bestappgallery.presenter.interfacePresenter;
import com.example.dale_c.bestappgallery.retrofit.ConnectGoogle;

/**
 * Created by Dale_C on 25.11.2017.
 */

public class Repository  {

    private static final String TAG = "Repository";
    private static final String URL_TRANSLATE = "https://translate.yandex.net/";
    private static final String URL_IMAGES = "https://api.qwant.com/";
    private ConnectGoogle conImage;
    private ConnectGoogle conTranslate ;




     public  Repository(){
         conImage = new ConnectGoogle(URL_IMAGES);
         conTranslate = new ConnectGoogle(URL_TRANSLATE);
    }

    public void conRetrofit(String word, interfacePresenter interfacePresenter, int n){
        Log.d(TAG, "conRetrofit: "+word);
        conImage.registerCallBack(interfacePresenter);
        conImage.getImage(word, n);

    }


}
