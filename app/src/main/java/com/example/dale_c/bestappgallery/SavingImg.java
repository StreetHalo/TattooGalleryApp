package com.example.dale_c.bestappgallery;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Dale_C on 21.12.2017.
 */

public class SavingImg implements Target {
    private final String name;
    private ImageView imageView;
    public static final String TAG= "TARGET";



    public SavingImg(String name, ImageView imageView) {
        this.name = name;
        this.imageView = imageView;
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        File file = new File("/storage/emulated/0/Pictures/Ink"+ "/" + name+".jpg");
        try {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, ostream);
            ostream.close();
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.e(TAG, "onBitmapLoaded: "+e);
        }
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        Log.e(TAG, "onBitmapFailed: ");
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
