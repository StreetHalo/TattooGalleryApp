package com.example.dale_c.bestappgallery.view.galleryFragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;


/**
 * Created by Dale_C on 07.12.2017.
 */

public class ScrollListener extends RecyclerView.OnScrollListener {
    public static final String TAG = "ScrollListener";
    public ScrollListener() {
        super();
        Log.d(TAG, "ScrollListener: HI");

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                System.out.println("The RecyclerView is not scrolling");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                System.out.println("Scrolling now");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                System.out.println("Scroll Settling");
                break;

        }
        Log.d(TAG, "onScrolled: ");

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.d(TAG, "onScrolled: "+dy);
    }
}
