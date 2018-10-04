package com.example.ashleyhwang.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class fourthActivity extends AppCompatActivity {
    private String fourthA = "";
    private static final String TAG = "fourthActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Question 4: Medium-hard");
        setContentView(R.layout.activity_fourth);
        getIncomingIntent();
        initRadioGroup();
    }
    private void initRadioGroup(){
        RadioGroup rg = (RadioGroup)findViewById(R.id.fourthrg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            //@Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bsucc = (RadioButton) findViewById(R.id.succ);
                RadioButton bembro = (RadioButton) findViewById(R.id.embro);
                RadioButton balgae = (RadioButton) findViewById(R.id.algae);

                if (bsucc.isChecked()){
                    fourthA = "Succulent Plants";
                }
                if (bembro.isChecked()){
                    fourthA = "Embryophyte Plants";
                }if (balgae.isChecked()){
                    fourthA = "Red Algae";
                }
                Intent myLocalIntent = getIntent();
                Bundle myData = new Bundle();
                myData.putString("4", fourthA);
                myLocalIntent.putExtras(myData);
                setResult(Activity.RESULT_OK, myLocalIntent);
                finish();
            }
        });
    }


    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: started.");

        if(getIntent().hasExtra("images") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras");
            String imageUrl = getIntent().getStringExtra("images");
            String imageName = getIntent().getStringExtra("image_name");
//            setImage(imageUrl, imageName);
        }
    }
    private void setImage(Integer imageUrl, String image_name){
        Log.d(TAG, "setImage: setting image and ");
        TextView name = findViewById(R.id.image_description);
        name.setText(image_name);
        ImageView image = findViewById(R.id.image);
        image.setImageResource(R.drawable.ketchup);
    }

}
