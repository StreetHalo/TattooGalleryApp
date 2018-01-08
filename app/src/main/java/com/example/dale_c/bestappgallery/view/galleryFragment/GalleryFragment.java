package com.example.dale_c.bestappgallery.view.galleryFragment;

import android.annotation.SuppressLint;
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
import com.example.dale_c.bestappgallery.view.InterfaceView;
import com.example.dale_c.bestappgallery.view.MainActivity;


/**
 * Created by Dale_C on 02.11.2017.
 */

@SuppressLint("ValidFragment")
public class GalleryFragment extends Fragment   {

    private static final String TAG = "galleryFragment";

    private RecyclerView recyclerView;

    private InterfaceView interfaceView;

    private String fragment;

    @SuppressLint("ValidFragment")
    public GalleryFragment(InterfaceView interfaceView, String fragment){
        this.interfaceView = interfaceView;
        this.fragment = fragment;
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
        if(fragment== MainActivity.FAV_GALLERY_FRAGMENT) setAdapter(new GalleryAdapter(interfaceView.getSavedPics(),interfaceView,MainActivity.FAV_GALLERY_FRAGMENT));

        return v;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    public void setAdapter(GalleryAdapter adapter){

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
      interfaceView.setGalleryToolbar();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
}