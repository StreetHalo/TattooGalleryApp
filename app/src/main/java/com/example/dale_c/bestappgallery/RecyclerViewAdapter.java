package com.example.dale_c.bestappgallery;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dale_c.bestappgallery.itemCativity.ItemActivity;
import com.example.dale_c.bestappgallery.json.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 25.11.2017.
 */




public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PhotoHolder>  {
    public static final String TAG = "Adapter";
    private List<Item> mItems = new ArrayList<>();
    Item galleryItem;


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
    public void onBindViewHolder(final PhotoHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");
        galleryItem = mItems.get(position);
        holder.mItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+mItems.get(position).getMedia());
                Intent intent = new Intent(holder.mItemImageView.getContext(),ItemActivity.class);
                intent.putExtra("URL",mItems.get(position).getMedia());
                view.getContext().startActivity(intent);
            }
        });
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
            mItemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
        }
    }

}





