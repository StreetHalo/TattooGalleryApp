package com.example.dale_c.bestappgallery.view.galleryFragment;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.view.mainActivity.ControlItemFragment;
import com.example.dale_c.bestappgallery.view.mainActivity.InterfaceView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

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
    private ControlItemFragment controlItemFragment;
    private String choosenFragment;


    public GalleryAdapter(List<?> mItems, ControlItemFragment controlItemFragment, String fragment){

        choosenFragment = fragment;
        this.controlItemFragment = controlItemFragment;
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
                        controlItemFragment.setSelectedPic(position);
                        controlItemFragment.setItemAdapter();
                    }
                });

                Picasso
                        .with(holder.itemView.getContext())
                        .load("https:"+galleryItem.getThumbnail())
                        .transform(new RoundedCornersTransformation(10,10))
                        .resize(350,400)
                        .placeholder(R.layout.loading_animation)
                        .into(holder.mItemImageView);
                break;
            case FAV_GALLERY_FRAGMENT:
                holder.mItemImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        controlItemFragment.setSelectedPic(position);
                        controlItemFragment.setFavAdapter();
                    }
                });
              favGalleryItem = (String) mItems.get(position);
                Picasso
                        .with(holder.itemView.getContext())
                        .load(new File("/storage/emulated/0/Pictures/Ink/"+favGalleryItem))
                        .placeholder(R.layout.loading_animation)
                        .transform(new RoundedCornersTransformation(10,0))
                        .resize(350,400)
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





