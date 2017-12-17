package com.example.dale_c.bestappgallery;

import android.content.Context;

import com.example.dale_c.bestappgallery.json.ParseGson;

/**
 * Created by Dale_C on 25.11.2017.
 */

public interface CallbackPresenter {
    void callingbackGson(ParseGson parseGson);
    void callingBackFragment(Context context, int position);

}
