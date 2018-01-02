package com.example.dale_c.bestappgallery.view.galleryFragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.view.InterfaceView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */




public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoHolder>  {
    public static final String TAG = "Adapter";
    private List<Item> mItems = new ArrayList<>();
    Item galleryItem;
    private int position;
    private InterfaceView interfaceView;



    public GalleryAdapter(List<Item> mItems, InterfaceView interfaceView){
        this.interfaceView = interfaceView;
        if(mItems!=null) this.mItems = mItems;
        Log.d(TAG, "GalleryAdapter: ");
    }
    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_adapter, parent, false);
        PhotoHolder vh = new PhotoHolder(view);
        return vh;
          }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, final int position) {

        galleryItem = mItems.get(position);
        Log.d(TAG, "onBindViewHolder: "+position);

        holder.mItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceView.setItemAdapter(position);
            }
        });
        Picasso
                .with(holder.itemView.getContext())
                .load("https:"+galleryItem.getThumbnail())
                .placeholder(R.layout.loading_animation)
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
            mItemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}





