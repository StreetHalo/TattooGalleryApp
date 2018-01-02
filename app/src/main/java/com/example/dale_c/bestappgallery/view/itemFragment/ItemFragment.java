package com.example.dale_c.bestappgallery.view.itemFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.view.InterfaceView;

/**
 * Created by Dale_C on 03.12.2017.
 */

@SuppressLint("ValidFragment")
public class ItemFragment extends Fragment {
    public static final String TAG = "ItemFragment";
    private RecyclerView recyclerView;
    private Button plusButton;
    private Button minusButton;
    private ItemAdapter adapter;
    private int position;
    private InterfaceView interfaceView;


    @SuppressLint("ValidFragment")
    public ItemFragment(InterfaceView interfaceView){
        this.interfaceView = interfaceView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(getActivity());
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_item_fragment, container, false);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };

        recyclerView = (RecyclerView) v.findViewById(R.id.photo_recycler_view);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);

         plusButton = (Button)v.findViewById(R.id.test);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPosition();
            }
        });

        minusButton = (Button)v.findViewById(R.id.testm);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prePosition();
            }
        });

        return v;
    }

    public void setAdapter(ItemAdapter adapter, int position){
        this.adapter =adapter;
        this.position = position;

    }

    private void nextPosition(){
    position +=1;
    recyclerView.scrollToPosition(position);
    }

    private void prePosition(){
        position -=1;
        if(position<=0)position=0;
        recyclerView.scrollToPosition(position);
    }

    public int getPosition(){
        return position;
    }



    @Override
    public void onResume() {
        super.onResume();
        interfaceView.setItemToolbar();
        recyclerView.scrollToPosition(position);
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        interfaceView.setGalleryToolbar();
    }
}