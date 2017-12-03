package com.example.dale_c.bestappgallery.mainCativity;

import android.support.v4.app.Fragment;

import com.example.dale_c.bestappgallery.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}
