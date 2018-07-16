package com.example.h_mamytov.newflickrphotogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by EdgeTech on 15.04.2018.
 * class that handles the networking in PhotoGallery
 *
 * FlickrFetchr will start off small with only two methods: getUrlBytes(String) and
 * getUrlString(String). The getUrlBytes(String) method fetches raw data from a URL and
 * returns it as an array of bytes. The getUrlString(String) method converts the result from
 * getUrlBytes(String) to a String.
 */

public class FlickrFetchr {

    //
    private static final String TAG = "FlickrFetchr";
    private static final String API_KEY = "23275965a3f41093fe4cf9129b32d1d0";
    private static final String FETCH_RECENTS_METHODS = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final Uri ENDPOINT = Uri
            .parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("method", FETCH_RECENTS_METHODS)
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s")
            .build();

    public static byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public static String getUrlString() throws IOException {
        return new String(getUrlBytes(ENDPOINT.toString()));
    }

    public static String getUrlDownloadSourceUrl(){
        return "https://farm1.staticflickr.com/2/1418878_1e92283336_m.jpg";

    }

    //method that builds an appropriate request URL and fetches its contents.
    public static List<MyData> downloadGalleryItems() {
        List<MyData> items = new ArrayList<>();

        try{
            String jsonString = getUrlString();
            Log.i(TAG, "Received JSON: " + jsonString);

            //parse JSON text into corresponding Java objects using the JSONObject(String) constructor.
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        }catch (IOException e){
            Log.e(TAG, "Failed to fetch items", e);
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;

    }

    //method that pulls out information for each photo
    private static void parseItems(List<MyData> items, JSONObject jsonBody) throws JSONException{

        //This array contains a collection of JSONObjects, each representing metadata for a single photo
        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++){
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            MyData item = new MyData();
            item.setId(photoJsonObject.getInt("id"));
            item.setCaption(photoJsonObject.getString("title"));

            if (!photoJsonObject.has("url_s")){
                continue;
            }
            item.setFarmId(photoJsonObject.getString("farm"));
            item.setSecret(photoJsonObject.getString("secret"));
            item.setServerId(photoJsonObject.getString("server"));
            item.setUrl(photoJsonObject.getString("url_s"));
            items.add(item);
        }
    }


    //=========================================================//
    private String buildUrl(String method, String query){
        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                .appendQueryParameter("method", method);

        if (method.equals(SEARCH_METHOD)){
            uriBuilder.appendQueryParameter("text", query);
        }

        return uriBuilder.build().toString();
    }

//    public List<MyData> fetchRecentPhotos(){
//        String url = buildUrl(FETCH_RECENTS_METHODS, null);
//        return downloadGalleryItems(url);
//    }
//
//    public List<MyData> searchPhotos(String query) {
//        String url = buildUrl(SEARCH_METHOD, query);
//        return downloadGalleryItems(url);
//    }
}
