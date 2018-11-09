package com.example.ashleyhwang.pp4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONNewsParser {
    private static final String TAG = "JSONNewsParser";
    public static News getNews(JSONObject jobj) throws JSONException {
        News news = new News();
        int totalResult= getInt("totalResults",jobj);
        Log.d(TAG, "getNews: "+totalResult);

        if (totalResult >0){
        JSONArray jArr = jobj.getJSONArray("articles");

//        for(int i = 0; i < 3 || i <jArr.length(); i++)
        JSONObject JSONNews = jArr.getJSONObject(0);
        news.setTitle(getString("title", JSONNews));
        news.setDesc(getString("description", JSONNews));
        news.setUrl(getString("url", JSONNews));

        return news;
        } return news;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}
