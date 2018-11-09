package com.example.ashleyhwang.pp4;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;

public class NewsHttpClient {
    private static String BASE_URL = "https://newsapi.org/v2/everything?q=";
    private static String APIkey = "&apiKey=ff0e8c3672ce4634a473a6a7b682e481";

    public JSONObject getNewsData(String title) {
        HttpURLConnection con = null;
        InputStream is = null;
        JSONObject jsonObject;
        try{
            String article = BASE_URL + title + APIkey;
            jsonObject = JSONfunctions.getJSONfromURL(article);
            return jsonObject;
        }
        catch ( Throwable t){
            t.printStackTrace();
        }
        finally {
            try{
                is.close();
            }
            catch(Throwable t){
            }
            try{
                con.disconnect();;
            }
            catch (Throwable t){
            }
        }
        return null;
    }
}
