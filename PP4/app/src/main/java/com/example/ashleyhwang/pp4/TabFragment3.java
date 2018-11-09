

package com.example.ashleyhwang.pp4;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment3 extends Fragment {
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<Dog> dogs;
    ArrayList<String> nameAndJob;
    private RecyclerView mRecyclerView;
    private DogViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView loading;
    private ProgressBar progressBar;
    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.tab_fragment3, container, false);
        setRetainInstance(true); // Retain this fragment on configuration changes.

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);  //note this need
        //progressBar.setProgress(0);

        loading = (TextView) rootView.findViewById(R.id.textView); //note this need
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view); //note this need

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //note this need

        // Download JSON file AsyncTask, note how i pass parameter for the progressBar to AsyncTask constructor
        //new DownloadJSON(progressBar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return rootView;
    }
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        //if (visible && isResumed())
        if (visible)
        {
            //Only manually call onVisible if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            //onVisible();
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        //if (!getUserVisibleHint())
        //{
        //    return;
        //}
        new DownloadJSON(progressBar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    // Download JSON file AsyncTask
    public class DownloadJSON extends AsyncTask<Void, Integer, Void> {
        ProgressBar bar;
        private final String BASE_URL= "https://api.thedogapi.com/v1/breeds/";
        private final String API_KEY ="ceec92bf-0c31-4fb7-8350-6c463254bb4e";
        public DownloadJSON(ProgressBar bar) {
            this.bar = bar;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setMax(100);
            bar.setProgress(0);
            bar.setVisibility(View.VISIBLE);
            loading.setText("Loading....");
            loading.setVisibility(View.VISIBLE);
            if (dogs != null) {
                dogs.clear();
            }
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }

        // parse JSON info and put it in Caricature object
        @Override
        protected Void doInBackground(Void... params) {
            if (dogs!= null) {
                dogs.clear();
            }
            dogs = new ArrayList<Dog>();
            jsonArray = JSONfunctions.getJSONArrayfromURL("https://api.thedogapi.com/v1/breeds?limit=10&page=0");
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    Dog doggo = new Dog();  //look at this
                    doggo.setId(jsonObject.getInt("id"));
                    doggo.setName(jsonObject.getString("name"));
                    dogs.add(doggo);
                    publishProgress();
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        // put results in ListView
        @Override
        protected void onPostExecute(Void args) {
//            ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
            loading.setText("List of breeds starting with a letter A");
//            loading.setText("Done");
//            loading.setVisibility(View.INVISIBLE);
            bar.setVisibility(View.INVISIBLE);

            // create an Object for Adapter
            mAdapter = new DogViewAdapter(dogs);

            mAdapter.notifyDataSetChanged();

            // set the adapter object to the Recyclerview
            mRecyclerView.setAdapter(mAdapter);

        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar.incrementProgressBy(20);
        }
    }
}
