package com.example.dale_c.bestappgallery.view.itemFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.view.mainActivity.ControlItemFragment;

/**
 * Created by Dale_C on 03.12.2017.
 */

public class ItemFragment extends Fragment {

    public static final String TAG = "ItemFragment";
    private RecyclerView recyclerView;
    private Button plusButton;
    private Button minusButton;
    private ImageButton likeButton;
    private ImageButton shareButton;
    private ItemAdapter adapter;
    private int position;
    private ControlItemFragment controlItemFragment;
    private Toolbar itemToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(getActivity());
        setRetainInstance(false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View v = inflater.inflate(R.layout.layout_item_fragment, container, false);

        itemToolbar = (Toolbar) v.findViewById(R.id.toolbar_item);
        likeButton = (ImageButton) v.findViewById(R.id.leftButton);
        shareButton = (ImageButton) v.findViewById(R.id.rightButton);
        plusButton = (Button)v.findViewById(R.id.test);
        minusButton = (Button)v.findViewById(R.id.testm);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };

        recyclerView = (RecyclerView) v.findViewById(R.id.photo_recycler_view);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(position);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPosition();
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prePosition();
            }
        });

        
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlItemFragment.saveLikePosition(getPosition());
                updateLikeButton();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: SHARE_BUTTON");
            }
        });
        if(controlItemFragment!=null)
        updateLikeButton();
        return v;
    }

    public void setAdapter(ItemAdapter adapter, int position, ControlItemFragment controlItemFragment){
        this.adapter =adapter;
        this.position = position;
        this.controlItemFragment = controlItemFragment;
        Log.d(TAG, "setAdapter: "+position);
        if(likeButton!=null)updateLikeButton();
    }

    private void nextPosition(){
    position +=1;
        updateLikeButton();

        recyclerView.scrollToPosition(position);
        controlItemFragment.setSelectedPic(position);
        controlItemFragment.setItemToolbar();
    }

    private void prePosition(){
        position -=1;
        if(position<=0)position=0;
        updateLikeButton();
        recyclerView.scrollToPosition(position);
        controlItemFragment.setSelectedPic(position);
        controlItemFragment.setItemToolbar();
    }

    public int getPosition(){
        return position;
    }

    private void updateLikeButton(){
     if(controlItemFragment.getItems().get(position).getLiked())
           likeButton.setImageResource(R.drawable.ic_check_black_24dp);
      else likeButton.setImageResource(R.drawable.ic_favorite_black_24dp);
    }


    @Override
    public void onResume() {
        super.onResume();
        recyclerView.scrollToPosition(position);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        try {
            controlItemFragment.delItemFragment();
        } catch (Exception o){
            Log.d(TAG, "onDestroy: Item");
        }
        if(controlItemFragment!=null)
            controlItemFragment.setGalleryToolbar();
     // interfaceView.uploadFavGalleryAdapter();

    }
}