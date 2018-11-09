package com.example.ashleyhwang.pp4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MovieHTTPClient {
    private static final String TAG = "MovieHTTPClient";
    private static String BASE_URL = "http://www.omdbapi.com/?t=";
    private static String PLOT_INFO= "&plot=full";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String APIkey = "&apikey=94149749";


    public JSONObject getMovieData(String movie) {
        HttpURLConnection con = null;
        InputStream is = null;
        JSONObject jsonObject;
        try {
            String movieInfo =BASE_URL + movie +PLOT_INFO+ APIkey;
            Log.d(TAG, "getMovieData: "+movieInfo);
            jsonObject = JSONfunctions.getJSONfromURL(movieInfo);
            Log.d(TAG, "getMovieData: success"+jsonObject.toString());
            return jsonObject;
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;

    }


    public Bitmap getImage(String code) {
        InputStream in = null;
        int resCode = -1;
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(IMG_URL + code + ".png");
            URLConnection urlConn = url.openConnection();
            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            //HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }

            Bitmap bitmap = null;
            bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Throwable t) {
            }
            try {
                httpConn.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }
}
