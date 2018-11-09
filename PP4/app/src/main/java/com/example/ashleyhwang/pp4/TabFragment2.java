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


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */

//Movie lookup
public class TabFragment2 extends Fragment {
    private static final String TAG = "TabFragment2";
    private EditText movieTitle;
    private TextView title;
    private TextView rated;
    private TextView year;
    private TextView genre;
    private TextView releaseDate;
    private TextView runtime;
    private TextView director;
    private TextView actors;
    private ImageView poster;
    private Button submit2;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.tab_fragment2, container, false);
        setRetainInstance(true); // Retain this fragment on configuration changes.
        movieTitle = (EditText) rootView.findViewById(R.id.movieTitle);
        title = (TextView) rootView.findViewById(R.id.title);
        year= (TextView) rootView.findViewById(R.id.year);
        genre = (TextView) rootView.findViewById(R.id.genre);
        releaseDate = (TextView) rootView.findViewById(R.id.releaseddate);
        runtime = (TextView) rootView.findViewById(R.id.runtime);
        director = (TextView) rootView.findViewById(R.id.director);
        actors= (TextView) rootView.findViewById(R.id.actors);
        poster = (ImageView) rootView.findViewById(R.id.poster);
        rated = (TextView) rootView.findViewById(R.id.rated);
        submit2 = (Button) rootView.findViewById(R.id.submit2);
        submit2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startTask(v);
            }
        });
        return rootView;
    }



    public void startTask(View view) {  //from the button
        JSONMovieTask task = new JSONMovieTask();
//        Toast.makeText(MainActivity.this, movieTitle.getText().toString(),Toast.LENGTH_LONG).show();
        task.execute(movieTitle.getText().toString());
        Log.d(TAG, "startTask: "+movieTitle.getText().toString());

    }

    public void setMenuVisility(final boolean visible){
        if(rootView != null){
            title.setText("");
            movieTitle.setText("");
            year.setText("");
            rated.setText("");
            genre.setText("");
            actors.setText("");
            director.setText("");
            runtime.setText("");
        }
    }

    private class JSONMovieTask extends AsyncTask<String, Void, Movie> {

        @Override
        protected Movie doInBackground(String... params) {
            Movie movie1 = new Movie();
            JSONObject jObj;
            Log.d(TAG, "doInBackground: got jobj");
            jObj = ((new MovieHTTPClient()).getMovieData(params[0]));
            try {
                //call JSON parser to extract data from JSONobject into weather object
                movie1 = JSONMovieParser.getMovie(jObj);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movie1;

        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            //TODO: Getting an image file.
//            if (movie.iconData != null && movie.iconData.getByteCount() > 0) {
//                poster.setImageBitmap(movie.iconData);
//            }
            title.setText("Movie Title: " + movie.getTitle());
            year.setText("Year: "+ movie.getYear());
            actors.setText("Featuring: "+movie.getActors());
            genre.setText("Genre of the movie: "+movie.getGenre());
            rated.setText("Rated:"+movie.getRated());
            director.setText("Director of the movie: "+movie.getDirector());
            releaseDate.setText("Movie was released in"+movie.getReleasedate());
            runtime.setText("Runtime of the movie: "+movie.getRuntime());


            Intent i = new Intent(getActivity(), movieImage.class);
            i.putExtra("movieObject", movie);
            ((Activity) getActivity()).startActivity(i);

        }
    }
}
