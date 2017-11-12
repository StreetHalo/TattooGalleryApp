package com.example.dale_c.bestappgallery;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dale_C on 02.11.2017.
 */

public class PhotoGalleryFragment extends Fragment {
    public static final String TAG = "PhotoGalleryFragment";


    private RecyclerView mPhotoRecyclerView;
    private List<PhotoItem> mItems = new ArrayList<>();
    private ConnectGoogle connectGoogle;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        updateSearch();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_photo_gallery,menu);

        MenuItem searchItem = menu.findItem(R.id.menu_img_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: "+query);
                QueryPreferences.setStoredQuery(getActivity(),query);
                 updateSearch();
                return true;
            }

            public boolean onOptionsItemSelected(MenuItem item){
                switch (item.getItemId()){
                    case R.id.menu_item_clear:
                        QueryPreferences.setStoredQuery(getActivity(),null);
                        updateSearch();
                        return true;
                    default:
                        return PhotoGalleryFragment.super.onOptionsItemSelected(item);
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: "+newText);
                return false;
            }
        });
    }
    private void updateSearch(){
        String query = QueryPreferences.getStoredQuery(getActivity());
        new httpGet(query).execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_photo_gallery, container, false);
        mPhotoRecyclerView = (RecyclerView) v.findViewById(R.id.photo_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        setupAdapter();
        return v;
    }

    private void setupAdapter() {
        if(isAdded())mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImageView;

        public PhotoHolder(View itemView){
            super(itemView);
            mItemImageView = (ImageView) itemView.findViewById(R.id.item_image_view);
        }

        public void bindDrawable(Drawable drawable){
            mItemImageView.setImageDrawable(drawable);
        }
    }


    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{

        private List<PhotoItem> mGalleryItems;

        public PhotoAdapter (List<PhotoItem> photoItems){
            mGalleryItems = photoItems;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.gallery_item, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            PhotoItem galleryItem = mGalleryItems.get(position);
            System.out.println("TTTT"+galleryItem.getmThumbnail());
            Picasso
                    .with(getActivity())
                   // .load(galleryItem.getmUrl())
                    .load("https:"+galleryItem.getmThumbnail())
                    .placeholder(R.drawable.wait)
                    .into(holder.mItemImageView);
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }


    private class httpGet extends AsyncTask<Void,Void,List<PhotoItem>>{

        private String mQuery;
        public httpGet(String query){
            mQuery = query;
        }

        @Override
        protected List<PhotoItem> doInBackground(Void... voids) {


           return new ConnectGoogle()
                   .takeJSON(mQuery);

        }

        @Override
        protected void onPostExecute(List<PhotoItem> photoItems) {
            mItems = photoItems;
            setupAdapter();
        }
    }
}