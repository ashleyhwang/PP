package com.example.ashleyhwang.pp4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMovieParser {
    private static final String TAG = "JSONMovieParser";
    public static Movie getMovie(JSONObject jObj) throws JSONException {
        Movie movie = new Movie();

        // We create our JSONObject from the data
        //JSONObject jObj = new JSONObject(data);
//        if(getString("Response",jObj)=="False"){
//
//        }
        movie.setTitle(getString("Title", jObj));
        movie.setYear(getString("Year", jObj));
        movie.setGenre(getString("Genre", jObj));
        movie.setActors(getString("Actors", jObj));
        movie.setRated(getString("Rated", jObj));
        movie.setRuntime(getString("Runtime", jObj));
        movie.setDirector(getString("Director", jObj));
        movie.setReleasedate(getString("Released", jObj));
        movie.setRuntime(getString("Runtime", jObj));
        movie.setImg_link(getString("Poster",jObj));
//        Log.d(TAG, "getMovie: "+ movie.getTitle());

        return movie;
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
