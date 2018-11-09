/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.ashleyhwang.pp4;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
//Top news
public class TabFragment1 extends Fragment {

    private TextView artTitle;
    private TextView artDesc;
    private TextView artURL;
    private Button submit;
    private Button find;
    private EditText searchArticle;
    private String urlAd;


    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.tab_fragment1, container, false);
        setRetainInstance(true);

        searchArticle = (EditText) rootView.findViewById(R.id.searchArt);
        artTitle = (TextView) rootView.findViewById(R.id.artTitle);
        artDesc = (TextView) rootView.findViewById(R.id.artDesc);
        artURL = (TextView) rootView.findViewById(R.id.artURL);
        submit = (Button) rootView.findViewById(R.id.button);
        find = (Button) rootView.findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    startTask(v);
            }
        });

        find.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startWebPage(v);
            }
        });

        return rootView;
    }
    @Override
    public void setMenuVisibility(final boolean visible) {
        if (rootView != null){
            artTitle.setText("");
            artDesc.setText("");
            artURL.setText("");
        }
        super.setMenuVisibility(visible);
    }


    public void startTask(View view){
        JSONNewsTask task = new JSONNewsTask();

        if (searchArticle.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please enter a keyword", Toast.LENGTH_SHORT).show();
        }else{
        task.execute(searchArticle.getText().toString());
        }
    }

    public void startWebPage(View v){
        if(urlAd!=null){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(urlAd));
        startActivity(intent);
        } else{
            Toast.makeText(getContext(), "Please enter a valid keyword and find the article first", Toast.LENGTH_SHORT).show();
        }
    }
    private class JSONNewsTask extends AsyncTask<String, Void, News> {
        @Override
        protected News doInBackground(String... params) {
            News news = new News();
            JSONObject jobj;
            jobj = ((new NewsHttpClient()).getNewsData(params[0]));
            try {
                news = JSONNewsParser.getNews(jobj);
            }
            catch (JSONException e){
            }
            return news;
        }

        @Override
        protected void onPostExecute(News news){
            super.onPostExecute(news);
            artTitle.setText("\nTitle \n" + news.getTitle());
            artDesc.setText("\nDescription \n" + news.getDesc());
            artURL.setText("\nurl \n" + news.getUrl());
            urlAd = news.getUrl();
        }
    }
}
