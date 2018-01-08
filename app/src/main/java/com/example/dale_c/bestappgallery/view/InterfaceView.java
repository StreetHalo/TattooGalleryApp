package com.example.dale_c.bestappgallery.view;

import android.widget.ImageView;

import com.example.dale_c.bestappgallery.json.Item;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by Dale_C on 19.12.2017.
 */

public interface InterfaceView {


    void setFragment(String s);

    void uploadGalleryAdapter();

    void uploadFavGalleryAdapter();

    void setItemAdapter();

    void setFavAdapter();

    List<String> getSavedPics();

    void setImageView(ImageView imageView);

    void setGalleryToolbar();

    void setItemToolbar();

    void setFavItemToolbar();

    void setSelectedPic(int position);

    void delFavPic();
}
