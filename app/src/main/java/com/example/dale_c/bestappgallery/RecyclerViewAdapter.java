package com.example.dale_c.bestappgallery;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dale_c.bestappgallery.PhotoGalleryFragment;
import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.json.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */




public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PhotoHolder> {
    public static final String TAG = "Adapter";
    private List<Item> mItems = new ArrayList<>();


    public RecyclerViewAdapter(List<Item> mItems){
        if(mItems!=null){
            Log.d(TAG, "RecyclerViewAdapter: notNull");
            this.mItems = mItems;
        }
        Log.d(TAG, "RecyclerViewAdapter: ");
    }
    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);

        PhotoHolder vh = new PhotoHolder(view);
        return vh;
          }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Item galleryItem = mItems.get(position);
        Picasso
                .with(holder.itemView.getContext())
                .load("https:"+galleryItem.getThumbnail())
                .placeholder(R.drawable.wait)
                .into(holder.mItemImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static  class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImageView;

        public PhotoHolder(View itemView){

            super(itemView);
            Log.d(TAG, "PhotoHolder: ");
            mItemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
        }
    }

}





