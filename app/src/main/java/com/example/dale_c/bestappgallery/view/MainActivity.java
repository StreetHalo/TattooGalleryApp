package com.example.dale_c.bestappgallery.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dale_c.bestappgallery.presenter.Presenter;
import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.view.itemFragment.ItemFragment;
import com.example.dale_c.bestappgallery.view.itemFragment.ItemAdapter;
import com.example.dale_c.bestappgallery.view.galleryFragment.GalleryFragment;
import com.example.dale_c.bestappgallery.view.galleryFragment.GalleryAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class MainActivity  extends AppCompatActivity implements InterfaceView {

    private static final String QUERIES = "Queries";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Set<String> queries = new HashSet<>();




    private static final String TAG = "Activity";
    public static final String ITEM_FRAGMENT = "itemFragment";
    public static final String FAV_ITEM_FRAGMENT = "favItemFragment";
    public static final String GALLERY_FRAGMENT = "galleryFragment";
    public static final String FAV_GALLERY_FRAGMENT = "favGalleryFragment";

   private FragmentTransaction fm;
   private Presenter presenter;
   private GalleryFragment galleryFragment;
   private GalleryFragment favGalleryFragment;
   private ItemFragment itemFragment;
   private ItemFragment favItemFragment;

   private ImageView imageView;
   private MenuItem  likeItem;
   private MenuItem searchItem;
   private MenuItem favGallery;
   private MenuItem delItem;
   private MenuItem shareItem;

   private Set<String> requests;
   private List<String> savePics;
   private Boolean isDel = false;
   private int selectedPic;
   private InputMethodManager imm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);

        presenter = Presenter.getInstance(getBaseContext());
        presenter.setInterfaceView(this);
        Toolbar mActionBarToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        requests = new HashSet<>();
        requests.addAll(presenter.getRequestsfrmSharedPref());
        Log.d(TAG, "onCreate requests: "+requests.size());
         galleryFragment = new GalleryFragment(this,GALLERY_FRAGMENT);
         itemFragment =  new ItemFragment(this, ITEM_FRAGMENT);
         favGalleryFragment = new GalleryFragment(this,FAV_GALLERY_FRAGMENT);
         favItemFragment = new ItemFragment(this, FAV_ITEM_FRAGMENT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);

        searchItem = menu.findItem(R.id.action_search);
        likeItem = menu.findItem(R.id.like_pic);
        favGallery = menu.findItem(R.id.fav_gallery);
        delItem = menu.findItem(R.id.del_pic);
        shareItem = menu.findItem(R.id.share_pic);
        setFragment(GALLERY_FRAGMENT);

      final AppCompatAutoCompleteTextView searchView = (AppCompatAutoCompleteTextView) searchItem.getActionView();

    searchView.setDropDownWidth(300);
      searchView.setWidth(750);
      searchView.setSingleLine();

      searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                searchView.setAdapter(new ArrayAdapter<String>
                        (searchView.getContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                requests.toArray(new String[requests.size()])));

                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                searchView.requestFocus();
                return true;
            }
        });


        likeItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

             if(itemFragment.isVisible()) {
                  presenter.saveLikePosition(itemFragment.getPosition(),imageView.getContext(), imageView);
                 setItemToolbar();
             }
                return true;
            }
        });

        delItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                favItemFragment.setDelDialog();
                return true;
            }
        });



        favGallery.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                presenter.updateSavedPic();
                Log.d(TAG, "onMenuItemClick: fav");
                setFragment(FAV_GALLERY_FRAGMENT);
                return true;
            }
        });

        shareItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Log.d(TAG, "onMenuItemClick: "+"Share Pic");
                return true;
            }
        });

        searchView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {


                if(keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER) {
                    searchView.clearFocus();

                    if (searchView.getText().toString().length() == 0) {

                        Toast.makeText
                                (MainActivity.this, "Введите запрос, пес", Toast.LENGTH_SHORT)
                                .show();
                    }

                    else {
                        Log.d(TAG, "onKey: "+searchView.getText().toString());
                        presenter.SearchWord(searchView.getText().toString());
                        imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);
                        requests.add(searchView.getText().toString());
                    }
                }
                return true;
            }
        });

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d(TAG, "onOptionsItemSelected: "+item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setFragment(String fragment) {
        fm = getSupportFragmentManager().beginTransaction();

        switch (fragment){

            case ITEM_FRAGMENT:
                fm.add(R.id.fragment_container,itemFragment,ITEM_FRAGMENT);
                setItemToolbar();
                break;

            case FAV_ITEM_FRAGMENT:
                fm.add(R.id.fragment_container,favItemFragment,FAV_ITEM_FRAGMENT);
                setFavItemToolbar();
                break;
            case GALLERY_FRAGMENT:
                if(favGalleryFragment.isVisible())
                    fm.replace(R.id.fragment_container,galleryFragment,GALLERY_FRAGMENT);
                else
                    fm.add(R.id.fragment_container,galleryFragment,GALLERY_FRAGMENT);
                setGalleryToolbar();
                break;
            case FAV_GALLERY_FRAGMENT:
                if(galleryFragment.isVisible())
                    fm.replace(R.id.fragment_container,favGalleryFragment,FAV_GALLERY_FRAGMENT);
                else
                    fm.add(R.id.fragment_container,favGalleryFragment,FAV_GALLERY_FRAGMENT);
                setGalleryToolbar();
                break;

            default:
                break;
        }
        fm.addToBackStack(null);
        fm.commit();
    }

    @Override
    public void uploadGalleryAdapter() {
        Log.d(TAG, "uploadGalleryAdapter: "+galleryFragment.isVisible());
        if(!galleryFragment.isVisible())setFragment(GALLERY_FRAGMENT);
        galleryFragment.setAdapter(new GalleryAdapter(presenter.getItems(),this, GALLERY_FRAGMENT));
    }

    @Override
    public void uploadFavGalleryAdapter() {
        savePics = presenter.getSavedPics();
        if(isDel) {
            savePics.remove(selectedPic);
            isDel = false;
        }
        if(favGalleryFragment.isVisible()){
            Log.d(TAG, "uploadFavGalleryAdapter: "+favGalleryFragment.isVisible());
        favGalleryFragment.setAdapter(new GalleryAdapter(savePics,this, FAV_GALLERY_FRAGMENT));}
    }

    @Override
    public void setItemAdapter() {
        Log.d(TAG, "setItemAdapter: "+ selectedPic);
        itemFragment.setAdapter(new ItemAdapter(presenter.getItems(),this, ITEM_FRAGMENT), selectedPic);
        setFragment(ITEM_FRAGMENT);
    }

    @Override
    public void setFavAdapter() {
        favItemFragment.setAdapter(new ItemAdapter(presenter.getSavedPics(),this,FAV_ITEM_FRAGMENT), selectedPic);
        setFragment(FAV_ITEM_FRAGMENT);
    }

    @Override
    public void setGalleryToolbar(){
        searchItem.setVisible(true);
        favGallery.setVisible(true);
        likeItem.setVisible(false);
        shareItem.setVisible(false);
        delItem.setVisible(false);
    }
    @Override
    public void setItemToolbar(){

     if(!presenter.getItems().get(selectedPic).getLiked()){
           likeItem.setIcon(R.drawable.ic_favorite_black_24dp);
           likeItem.setEnabled(true);
        }
        else {
         likeItem.setIcon(R.drawable.ic_check_black_24dp);
         likeItem.setEnabled(false);
        }
        shareItem.setVisible(true);
        likeItem.setVisible(true);
        favGallery.setVisible(false);
        searchItem.setVisible(false);
        searchItem.collapseActionView();
    }

    @Override
    public void setFavItemToolbar(){
        delItem.setVisible(true);
        shareItem.setVisible(true);
        likeItem.setVisible(false);
        favGallery.setVisible(false);
        searchItem.setVisible(false);
        searchItem.collapseActionView();
    }


    @Override
    public void setImageView(ImageView imageView) {

        this.imageView = imageView;
    }


    @Override
    public void setSelectedPic(int position) {

        selectedPic = position;
    }

    @Override
    public void delFavPic() {
        presenter.delPic(selectedPic);
        isDel = true;
        onBackPressed();
    }


    @Override
    public List<String> getSavedPics(){

        return presenter.getSavedPics();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+requests);
      presenter.setRequestsfrmSharedPref(requests);
    }

}
