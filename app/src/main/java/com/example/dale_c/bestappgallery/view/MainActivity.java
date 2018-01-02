package com.example.dale_c.bestappgallery.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.ImageView;

import com.example.dale_c.bestappgallery.json.Item;
import com.example.dale_c.bestappgallery.presenter.Presenter;
import com.example.dale_c.bestappgallery.R;
import com.example.dale_c.bestappgallery.view.itemFragment.ItemFragment;
import com.example.dale_c.bestappgallery.view.itemFragment.ItemAdapter;
import com.example.dale_c.bestappgallery.view.galleryFragment.GalleryFragment;
import com.example.dale_c.bestappgallery.view.galleryFragment.GalleryAdapter;

import java.util.List;

public class MainActivity  extends AppCompatActivity implements InterfaceView {
    public static final String TAG = "Activity";
    public static final String ITEM_FRAGMENT = "itemFragment";
    public static final String GALLERY_FRAGMENT = "galleryFragment";
    public static final String FAV_GALLERY_FRAGMENT = "favGalleryFragment";

    FragmentTransaction fm;
    Presenter presenter;
    GalleryFragment galleryFragment;
    GalleryFragment favGalleryFragment;
    ItemFragment itemFragment;
    private String layoutFragment;
    private ImageView imageView;
    private MenuItem  likeItem;
    private MenuItem searchItem;
    private MenuItem favGallery;

   private List<Item> items;
    InputMethodManager imm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        layoutFragment = "OhHiMark";

        Toolbar mActionBarToolbar = (Toolbar)findViewById(R.id.toolbar);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setSupportActionBar(mActionBarToolbar);

         presenter = Presenter.getInstance();
         presenter.setInterfaceView(this);

         galleryFragment = new GalleryFragment(this);
         itemFragment =  new ItemFragment(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {getMenuInflater().inflate(R.menu.menu_main_activity,menu);

       searchItem = menu.findItem(R.id.action_search);

        likeItem = menu.findItem(R.id.action_like);
        favGallery = menu.findItem(R.id.fav_gallery);

        setGalleryToolbar();

     // final SearchView searchView = (SearchView) searchItem.getActionView();
      final AppCompatAutoCompleteTextView searchView = (AppCompatAutoCompleteTextView) searchItem.getActionView();

        searchView.setDropDownWidth(300);
       searchView.setWidth(750);
       searchView.setSingleLine();
        //
     //   searchView.setFocusableInTouchMode(true);

        Log.d(TAG, "onCreateOptionsMenu: "+searchView);
        final String[] mCats = { "Мурзик", "Рыжик", "Барсик", "Борис",
                "Мурзилка", "Мурка" };

        searchView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mCats));


        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
             imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                searchView.requestFocus();

                return true;
            }
        });


        likeItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick: ");
             if(layoutFragment.equals(ITEM_FRAGMENT))
             presenter.saveLikePosition(itemFragment.getPosition(),imageView.getContext(), imageView);
                return true;
            }
        });


   /*     searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        //    presenter.SearchWord(searchView.getText().toString());
              //  if(imm.getCurrentInputMethodSubtype().toString()=="RESULT_HIDDEN")
                Log.d(TAG, "onClick: ");
              searchView.clearFocus();
            }
        });*/

        searchView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(TAG, "onKey: "+keyEvent.getKeyCode());


                if(keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                    Log.d(TAG, "onKey23: ");
                    searchView.clearFocus();

                    presenter.SearchWord(searchView.getText().toString());
                imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);}
                return true;
            }
        });


        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                presenter.SearchWord(query);
                searchView.clearFocus();
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.foundOldRequest(newText);

                return true;
            }
        });*/


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void setFragment(String s) {
        fm = getSupportFragmentManager().beginTransaction();


        switch (s){

            case ITEM_FRAGMENT:
                fm.add(R.id.fragment_container,itemFragment,ITEM_FRAGMENT);
                setItemToolbar();
                break;
            case GALLERY_FRAGMENT:
                fm.add(R.id.fragment_container,galleryFragment,GALLERY_FRAGMENT);
                setGalleryToolbar();
                break;
            case FAV_GALLERY_FRAGMENT:
                fm.add(R.id.fragment_container,favGalleryFragment);
                break;

            default:
                break;
        }
        fm.addToBackStack(null);
        fm.commit();
        layoutFragment = s;
    }

    @Override
    public void setGalleryAdapter(List<Item> items) {
        this.items = items;
       if(galleryFragment.getTag()==null) setFragment(GALLERY_FRAGMENT);
        else galleryFragment.setAdapter(new GalleryAdapter(items,this));

    }
    @Override
    public List<Item> getItems() {
        return items;
    }
    @Override
    public void setItemAdapter(int position) {
        itemFragment.setAdapter(new ItemAdapter(presenter.getItems(),this),position);
        setFragment(ITEM_FRAGMENT);

    }

    @Override
    public void setImageView(ImageView imageView) {

        this.imageView = imageView;
    }

    @Override
    public void setGalleryToolbar(){
        likeItem.setIcon(R.drawable.ic_favorite_black_24dp);
        favGallery.setIcon(R.drawable.ic_photo_library_black_24dp);
        likeItem.setVisible(true);
        searchItem.setVisible(true);
        favGallery.setVisible(true);
    }


    @Override
    public void setItemToolbar(){
        likeItem.setIcon(R.drawable.ic_favorite_black_24dp);
        likeItem.setVisible(true);
        favGallery.setVisible(false);
        searchItem.setVisible(false);
        searchItem.collapseActionView();
    }
}
