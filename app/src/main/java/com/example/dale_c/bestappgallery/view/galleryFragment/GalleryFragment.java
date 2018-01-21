package com.example.dale_c.bestappgallery.view.galleryFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.view.mainActivity.ControlItemFragment;
import com.example.dale_c.bestappgallery.view.mainActivity.InterfaceView;
import com.example.dale_c.bestappgallery.view.mainActivity.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dale_C on 02.11.2017.
 */

public class GalleryFragment extends Fragment    {

    private static final String TAG = "galleryFragment";

    private RecyclerView recyclerView;

    private ControlItemFragment controlItemFragment;

    private List<Item> items = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(getActivity());
        setRetainInstance(false);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_gallery_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.photo_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setAdapter(new GalleryAdapter(items,controlItemFragment, MainActivity.GALLERY_FRAGMENT));


        return v;
    }


    public void uploadGalleryFragment(ControlItemFragment controlItemFragment, List<Item> items){
        this.items = items;
        this.controlItemFragment = controlItemFragment;
        if(recyclerView!=null) setAdapter(new GalleryAdapter(items,controlItemFragment, MainActivity.GALLERY_FRAGMENT));
    }


    private void setAdapter(GalleryAdapter adapter){
        recyclerView.setAdapter(adapter);
    }
}