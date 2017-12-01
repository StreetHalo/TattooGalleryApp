package com.example.dale_c.bestappgallery;

import android.graphics.drawable.Drawable;
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

import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.json.Result;
import com.example.dale_c.bestappgallery.retrofit.ConnectGoogle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dale_C on 02.11.2017.
 */

public class PhotoGalleryFragment extends Fragment {
    public static final String TAG = "PGF ";


    private List<Item> mItems = new ArrayList<>();
    private RecyclerView recyclerView;
    Presenter presenter;

    public static PhotoGalleryFragment newInstance() {

        return new PhotoGalleryFragment();

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(getActivity());
        presenter = new Presenter(this);

        setRetainInstance(true);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_photo_gallery, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.photo_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        return v;
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
                presenter.SearchWord(query);
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




    public void setAdapter(RecyclerViewAdapter adapter){
        Log.d(TAG, "setAdapter: ");
        recyclerView.setAdapter(adapter);
    }
}