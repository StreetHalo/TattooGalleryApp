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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */




public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoHolder>  {

    private static final String TAG = "Adapter";
    private static final String GALLERY_FRAGMENT = "galleryFragment";
    private static final String FAV_GALLERY_FRAGMENT = "favGalleryFragment";

    private List<?> mItems = new ArrayList<>();
    private Item galleryItem;
    private String favGalleryItem;
    private int position;
    private InterfaceView interfaceView;
    private String choosenFragment;



    public GalleryAdapter(List<?> mItems, InterfaceView interfaceView, String fragment){
        Log.d(TAG, "GalleryAdapter: "+mItems.size());
        choosenFragment = fragment;
        this.interfaceView = interfaceView;
        if(mItems!=null) this.mItems = mItems;
    }



    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_adapter, parent, false);
        PhotoHolder vh = new PhotoHolder(view);
        return vh;
          }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, final int position) {

        switch (choosenFragment){
            case GALLERY_FRAGMENT:
                galleryItem = (Item) mItems.get(position);
                holder.mItemImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interfaceView.setSelectedPic(position);
                        interfaceView.setItemAdapter();
                    }
                });
                Log.d(TAG, "onBindViewHolder: "+position);

                Picasso
                        .with(holder.itemView.getContext())
                        .load("https:"+galleryItem.getThumbnail())
                        .placeholder(R.layout.loading_animation)
                        .into(holder.mItemImageView);



                break;
            case FAV_GALLERY_FRAGMENT:
                holder.mItemImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interfaceView.setSelectedPic(position);
                        interfaceView.setFavAdapter();
                    }
                });
              favGalleryItem = (String) mItems.get(position);
                Log.d(TAG, "onBindViewHolderFav: "+favGalleryItem);
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
            mItemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
        }
    }

}





