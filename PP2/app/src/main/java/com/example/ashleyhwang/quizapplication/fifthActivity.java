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

public class fifthActivity extends AppCompatActivity {
    private static final String TAG = "fifthActivity";
    public static String fifthA ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Question 5: Difficult");
        setContentView(R.layout.activity_fifth);
        getIncomingIntent();
        initRadioGroup();
    }
    private void initRadioGroup(){
        RadioGroup rg = (RadioGroup)findViewById(R.id.fifthrg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            //@Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton Re = (RadioButton) findViewById(R.id.Reimann);
                RadioButton r = (RadioButton) findViewById(R.id.relative);
                RadioButton f = (RadioButton) findViewById(R.id.fermat);
                RadioButton c = (RadioButton) findViewById(R.id.conjecture);

                if (Re.isChecked()){
                    fifthA = "Reimann Hypothesis";
                }
                if (r.isChecked()){
                    fifthA = "Einstein's Theory of Relativity";
                }if (f.isChecked()){
                    fifthA = "Fermat’s Last Theorem";
                }if (c.isChecked()){
                    fifthA = "Poincaré Conjecture";
                }
                Intent myLocalIntent = getIntent();
                Bundle myData = new Bundle();
                myData.putString("5", fifthA);
                myLocalIntent.putExtras(myData);
                setResult(Activity.RESULT_OK, myLocalIntent);
                Log.d(TAG, "onCheckedChanged: fifthActivity data sent");
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
        }
    }
    private void setImage(Integer imageUrl, String image_name){
        Log.d(TAG, "setImage: setting image and ");
        TextView name = findViewById(R.id.image_description);
        name.setText(image_name);
        ImageView image = findViewById(R.id.image);
        image.setImageResource(R.drawable.graphs);
    }
}
