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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */




public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.PhotoHolder>  {

    private static final String TAG = "ItemAdapter";
    private static final String ITEM_FRAGMENT = "itemFragment";
    private static final String FAV_ITEM_FRAGMENT = "favItemFragment";
    private List<?> mItems = new ArrayList<>();
    private Item galleryItem;
    private String favGalleryItem;
    private InterfaceView interfaceView;
    private String choosenFragment;

    public ItemAdapter(List<?> mItems, InterfaceView interfaceView, String fragment){
        choosenFragment = fragment;
        this.interfaceView = interfaceView;
        if(mItems!=null) this.mItems = mItems;
    }


    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false);
        PhotoHolder vh = new PhotoHolder(view);
        return vh;
          }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, final int position) {


       switch (choosenFragment) {

           case ITEM_FRAGMENT:
               Log.d(TAG, "ITEM_FRAGMENT: ");
               galleryItem = (Item) mItems.get(position);
               interfaceView.setImageView(holder.mItemImageView);
               interfaceView.setItemToolbar();

               Picasso
                   .with(holder.itemView.getContext())
                   .load(galleryItem.getMedia())
                   .placeholder(R.layout.loading_animation)
                   .into(holder.mItemImageView);
           break;

           case FAV_ITEM_FRAGMENT:

               Log.d(TAG, "FAV_ITEM_FRAGMENT "+position);
               favGalleryItem = (String) mItems.get(position);
               interfaceView.setImageView(holder.mItemImageView);
               interfaceView.setFavItemToolbar();

               Log.d(TAG, "onBindViewHolder: "+favGalleryItem);
               Picasso
                       .with(holder.itemView.getContext())
                       .load(new File("/storage/emulated/0/Pictures/Ink/"+favGalleryItem))
                       .placeholder(R.layout.loading_animation)
                       .into(holder.mItemImageView);
               break;
           default:
               break;
       }
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

}





