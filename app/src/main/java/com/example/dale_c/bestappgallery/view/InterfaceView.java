package com.example.dale_c.bestappgallery.view;

import android.widget.ImageView;

import com.example.dale_c.bestappgallery.json.Item;

import java.util.List;

/**
 * Created by Dale_C on 19.12.2017.
 */

public interface InterfaceView {

    void setFragment(String s);

    void setGalleryAdapter(List<Item> items);

    void setItemAdapter(int position);

    List<Item> getItems();

    void setImageView(ImageView imageView);

    void setItemToolbar();

    void setGalleryToolbar();
}
