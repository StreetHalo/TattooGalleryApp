package com.example.dale_c.bestappgallery.view.galleryFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.view.InterfaceView;


/**
 * Created by Dale_C on 02.11.2017.
 */

@SuppressLint("ValidFragment")
public class GalleryFragment extends Fragment   {

    public static final String TAG = "galleryFragment";

    private RecyclerView recyclerView;

    private InterfaceView interfaceView;


    @SuppressLint("ValidFragment")
    public GalleryFragment(InterfaceView interfaceView){
        this.interfaceView = interfaceView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        recyclerView = new RecyclerView(getActivity());
        setRetainInstance(true);
        

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_gallery_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.photo_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setAdapter(new GalleryAdapter(interfaceView.getItems(),interfaceView));
        return v;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    public void setAdapter(GalleryAdapter adapter){

        recyclerView.setAdapter(adapter);
    }

    public void setAdapter(FavGalleryAdapter adapter){
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        interfaceView.setGalleryToolbar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


}