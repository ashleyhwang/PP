package com.example.ashleyhwang.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class firstActivity extends AppCompatActivity {
    public static String firstA ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setTitle("First Question: easy");
        getIncomingIntent();
        initRadioGroup();
    }
    private void initRadioGroup(){
        RadioGroup rg = (RadioGroup)findViewById(R.id.firstrg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            //@Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton button_atlantic = (RadioButton) findViewById(R.id.atlantic_cod);
            RadioButton button_copper = (RadioButton) findViewById(R.id.copper);
            RadioButton button_tomatoes = (RadioButton) findViewById(R.id.tomatoes);
            RadioButton button_bananas = (RadioButton) findViewById(R.id.bananas);
            if (button_atlantic.isChecked()==true){
                firstA = "atlantic fish";
            }
            if (button_copper.isChecked()==true){
                firstA = "copper";
            }if (button_tomatoes.isChecked()==true){
                firstA = "tomatoes";
            }if (button_bananas.isChecked()==true){
                firstA = "bananas";
            }
            Intent myLocalIntent = getIntent();
            Bundle myData = new Bundle();
            myData.putString("1", firstA);
            myLocalIntent.putExtras(myData);
            setResult(Activity.RESULT_OK, myLocalIntent);
            finish();
        }
        });
    }
    private void getIncomingIntent(){

        if(getIntent().hasExtra("images") && getIntent().hasExtra("image_name")){
            String imageUrl = getIntent().getStringExtra("images");
            String imageName = getIntent().getStringExtra("image_name");
//            setImage(imageUrl, imageName);
        }
    }
    private void setImage(Integer imageUrl, String image_name){
        TextView name = findViewById(R.id.image_description);
        name.setText(image_name);
        ImageView image = findViewById(R.id.image);
        image.setImageResource(R.drawable.ketchup);
    }
}
