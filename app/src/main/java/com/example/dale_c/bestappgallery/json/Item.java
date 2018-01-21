
package com.example.dale_c.bestappgallery.json;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.content.ContentValues.TAG;

public class Item implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("thumb_width")
    @Expose
    private int thumbWidth;
    @SerializedName("thumb_height")
    @Expose
    private int thumbHeight;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("media_fullsize")
    @Expose
    private String mediaFullsize;
    @SerializedName("thumb_type")
    @Expose
    private String thumbType;
    @SerializedName("count")
    @Expose
    private int count;

    private boolean liked=false;

    public Item(Parcel in) {

        String[] data = new String[6] ;
        in.readStringArray(data);

    }

    public Boolean getLiked() {
        Log.d(TAG, "setLiked2: " + liked);

        return liked;
    }

    public void setLiked() {
      if (liked) liked = false;
       else liked = true;
        Log.d(TAG, "setLiked1: " + this.liked);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(int thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public int getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(int thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getMediaFullsize() {
        return mediaFullsize;
    }

    public void setMediaFullsize(String mediaFullsize) {
        this.mediaFullsize = mediaFullsize;
    }

    public String getThumbType() {
        return thumbType;
    }

    public void setThumbType(String thumbType) {
        this.thumbType = thumbType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.thumbnail,
                this.id,
                this.title,
                this.width,
                this.height,
                this.width,
                this.media
                });
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}