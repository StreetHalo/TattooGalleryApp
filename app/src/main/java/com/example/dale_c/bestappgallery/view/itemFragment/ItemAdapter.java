package com.example.dale_c.bestappgallery.view.itemFragment;

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




public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.PhotoHolder>  {
    private static final String TAG = "ItemAdapter";
    private List<Item> mItems = new ArrayList<>();
    private  Item galleryItem;
    private int position;
    private InterfaceView interfaceView;


    public ItemAdapter(List<Item> mItems, InterfaceView interfaceView){
        this.interfaceView = interfaceView;
        if(mItems!=null) this.mItems = mItems;
    }
    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false);
        PhotoHolder vh = new PhotoHolder(view);
        return vh;
          }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");
        galleryItem = mItems.get(position);
       // interfaceView.setImageView(holder.mItemImageView);

        Picasso
                .with(holder.itemView.getContext())
                .load(galleryItem.getMedia())
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
            mItemImageView = (ImageView) itemView.findViewById(R.id.test);
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}





