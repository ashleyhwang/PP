package com.example.ashleyhwang.pp4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class dogImage extends AppCompatActivity{
    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;
    String urlStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dogg_image);
//
        TextView name = (TextView) findViewById(R.id.heading);
        TextView weight = (TextView) findViewById(R.id.weight);
        TextView height = (TextView) findViewById(R.id.height);
        TextView bred_for = (TextView) findViewById(R.id.bredFor);
        TextView breed_group = (TextView) findViewById(R.id.breedGroup);
        TextView life_span = (TextView) findViewById(R.id.LIFESPAN);
        ImageView picture = (ImageView) findViewById(R.id.imageView);
        ProgressBar imageProgressBar = (ProgressBar) findViewById(R.id.progressBar2);


        Intent i = getIntent();
        final Dog received = (Dog) i.getSerializableExtra("dogObject");

        name.setText(received.getName());
        weight.setText(received.getWeight());
        height.setText(received.getHeight());
        life_span.setText(received.getLife_span());
        bred_for.setText(received.getBred_for());
        breed_group.setText(received.getBreed_group());
//        new LoadImageFromURL(picture, imageProgressBar).execute(received.getImg_link());
    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    private class LoadImageFromURL extends AsyncTask<String, Integer, Bitmap> {
        ImageView bitmapImgView;
        ProgressBar myProgressBar;

        public LoadImageFromURL(ImageView bmImgView, ProgressBar bar){
            bitmapImgView = bmImgView;
            myProgressBar = bar;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myProgressBar.setMax(100);
            myProgressBar.setProgress(0);
        }

        // download image
        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            String urlStr = params[0];
            InputStream in = null;
            try {
                in = openHttpConnection(urlStr);
                publishProgress();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                bitmap = BitmapFactory.decodeStream(in);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ee) {
                }

                publishProgress();
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute (Bitmap bitmap){
            bitmapImgView.setImageBitmap(bitmap);
            TextView loading = (TextView)findViewById(R.id.textView3);
            loading.setVisibility(View.INVISIBLE);
            myProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            myProgressBar.incrementProgressBy(20);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_caricature_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
