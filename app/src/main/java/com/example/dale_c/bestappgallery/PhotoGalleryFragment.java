package com.example.dale_c.bestappgallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.dale_c.bestappgallery.retrofit.ConnectGoogle;


/**
 * Created by Dale_C on 02.11.2017.
 */

public class PhotoGalleryFragment extends Fragment {
    public static final String TAG = "PhotoGalleryFragment";
    public static final String URL_TRANSLATE = "https://translate.yandex.net/";
    public static final String URL_IMAGES = "https://api.qwant.com/";


    ConnectGoogle conImage = new ConnectGoogle(URL_IMAGES);
    ConnectGoogle conTranslate = new ConnectGoogle(URL_TRANSLATE);


    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        conImage.getImage();

    }


  /*  @Override
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
                return true;
            }

            public boolean onOptionsItemSelected(MenuItem item){
                switch (item.getItemId()){
                    case R.id.menu_item_clear:
                        QueryPreferences.setStoredQuery(getActivity(),null);
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
            //Picasso
                 //   .with(getActivity())
             //       .load("https:")
                //    .placeholder(R.drawable.wait)
              //      .into(holder.mItemImageView);
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }
*/
}