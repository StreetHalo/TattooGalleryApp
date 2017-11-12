package com.example.dale_c.bestappgallery;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dale_C on 05.11.2017.
 */

public class ConnectGoogle {

//    public static final String URL = "https://api.qwant.com/api/search/images?count=220&offset=1&q=";
    public static final String URL = "https://api.qwant.com/api/search/images?count=150&offset=";
    public static final String TAG = "ConnectDog";

    private String request ;
    private int offset = 0;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connect = (HttpURLConnection)url.openConnection();
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connect.getInputStream();
            if(connect.getResponseCode()!=HttpURLConnection.HTTP_OK){
                throw new IOException(connect.getResponseMessage()+": with "+urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[9000];
            while ((bytesRead = in.read(buffer))>0){
                out.write(buffer,0,bytesRead);
            }
            out.close();
            return out.toByteArray();
        }
        finally {
            connect.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public List<PhotoItem> takeJSON(String request){
        List<PhotoItem> items = new ArrayList<>();
        try {

            String jsonString = getUrlString(URL+offset+"&q="+"tattoo_ideas_"+URLEncoder.encode(request, "UTF-8"));


            JSONObject jsonBody = new JSONObject(jsonString);

            parseItems(items,jsonBody);
        } catch (IOException e) {
        Log.e(TAG,"ERROR! "+e.toString());
        } catch (JSONException e) {
            Log.e(TAG,"ERROR JSON! "+e.toString());
        }
        return items;
    }

    private void parseItems(List<PhotoItem> items, JSONObject jsonBody) throws IOException,JSONException{
        JSONObject photosJsonObject = jsonBody.getJSONObject("data").getJSONObject("result");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("items");
            System.out.println("ARRAY  "+photoJsonArray.length());

       for(int i = offset; i < photoJsonArray.length(); i++){
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            PhotoItem item = new PhotoItem();
            item.setmId(photoJsonObject.getString("_id"));
            item.setmCaption(photoJsonObject.getString("title"));
            item.setmThumbnail(photoJsonObject.getString("thumbnail"));

            if(!photoJsonObject.has("media")){
                continue;
            }

            item.setmUrl(photoJsonObject.getString("media"));
            items.add(item);

        }
    }
}
