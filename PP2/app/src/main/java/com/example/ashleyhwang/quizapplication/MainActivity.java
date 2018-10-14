package com.example.ashleyhwang.quizapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {
    private static final String TAG = "MainActivity";
    Button submit;
    RecyclerViewAdapter adapter;

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private double correctP; // percentage of how many of the answer you got right
    //private ArrayList<String> answersArray = new ArrayList<>(5);

    private String [] answers = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");
        initImageBitMaps();
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Set<String> AnswerSet = new HashSet<>();
                AnswerSet.add("tomatoes");
                AnswerSet.add("PyeongChang");
                AnswerSet.add("Cabbage");
                AnswerSet.add("Reimann Hypothesis");
                AnswerSet.add("Succulent Plants");
                int count=0;
                int correctCount=0;

                //checks how many answers you got right
                while(count<5){
                    if(answers[count]==null){
                        Toast.makeText(MainActivity.this,
                                "Seems like you haven't answered all the questions yet!\nPlease answer the question number: "+(count+1),
                                Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        if(AnswerSet.contains(answers[count])){
                            correctCount ++;
                        }
                    }
                    count++;
                }
                //If you finished the quiz
                if (count==5){
                    //calculates the percentage
                correctP = ((double) correctCount/5) *100;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Quiz Summary");
                builder.setMessage("Your total score is "+correctP+"%.\nCorrect answers are: "+AnswerSet+"\nYour answers are: "+Arrays.toString(answers));
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Retry!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        answers = new String[5];
                    }
                });
                builder.show();
            }}
        });

    }

    private void initImageBitMaps(){
        mImageUrls.add(R.drawable.ketchup);
        mNames.add("First question");
        mImageUrls.add(R.drawable.suho);
        mNames.add("Second question");
        mImageUrls.add(R.drawable.creampuff);
        mNames.add("Third question");
        mImageUrls.add(R.drawable.cactus);
        mNames.add("Fourth question");
        mImageUrls.add(R.drawable.math);
        mNames.add("Fifth question");
        initRecyclerView();
    }

    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        adapter = new RecyclerViewAdapter( mNames, mImageUrls,this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: item has been clicked");
        Bundle myData;
        //if first question was clicked
        if(position==0) {
            Intent firstQIntent = new Intent(this, firstActivity.class);
            myData = new Bundle();
            myData.putInt("myRequestCode", 101);
            startActivityForResult(firstQIntent,101);
        } else if (position==1){
            Intent secondQIntent = new Intent(this, secondActivity.class);
            myData = new Bundle();
            myData.putInt("myRequestCode", 102);
            startActivityForResult(secondQIntent, 102);
        } else if (position ==2){ //third question
            Intent thirdQIntent = new Intent(this, thirdActivity.class);
            myData = new Bundle();
            myData.putInt("myRequestCode", 103);
            startActivityForResult(thirdQIntent, 103);
        } else if (position ==3){
            Intent fourthQIntent = new Intent(this, fourthActivity.class);
            myData = new Bundle();
            myData.putInt("myRequestCode", 104);
            startActivityForResult(fourthQIntent,104);
        } else if (position ==4) { // fifth question
            Intent fifthQIntent = new Intent(this, fifthActivity.class);
            myData = new Bundle();
            myData.putInt("myRequestCode",105);
            startActivityForResult(fifthQIntent, 105);
        }
    }
    ///////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode, data);
        if((requestCode ==101) && (resultCode == Activity.RESULT_OK)){
            Bundle myResults1 = data.getExtras();
            String result1 = myResults1.getString("1");
//            answersArray.add(0,result1);
            answers[0] = result1;
            Toast.makeText(this, "just added the item "+result1, Toast.LENGTH_SHORT).show();
            // array list
        }
        if ((requestCode ==102) && (resultCode == Activity.RESULT_OK)){
            Bundle myResults2 = data.getExtras();
            String result2 = myResults2.getString("2");
//            answersArray.add(1,result2);
            answers[1] = result2;
            Toast.makeText(this, "just added the item "+result2, Toast.LENGTH_SHORT).show();
            // array list
        }
        if ((requestCode ==103) && (resultCode == Activity.RESULT_OK)){
            Bundle myResults3 = data.getExtras();
            String result3 = myResults3.getString("3");
//            answersArray.add(2,result3);
            answers[2] = result3;
            Toast.makeText(this, "just added the item "+result3, Toast.LENGTH_SHORT).show();
            // array list
        }if ((requestCode ==104) && (resultCode == Activity.RESULT_OK)) {
                Bundle myResults4 = data.getExtras();
                String result4 = myResults4.getString("4");
            answers[3] = result4;
            Toast.makeText(this, "just added the item " + result4, Toast.LENGTH_SHORT).show();
            }
        if ((requestCode == 105) && (resultCode == Activity.RESULT_OK)) {
            Bundle myResults5 = data.getExtras();
            String result5 = myResults5.getString("5");
            answers[4] = result5;
//            answersArray.add(4, result5);
            Toast.makeText(this, "just added the item " + result5, Toast.LENGTH_SHORT).show();
        }
    }
}
