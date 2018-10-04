package com.example.ashleyhwang.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class secondActivity extends AppCompatActivity {
    private String secondA="";
    private static final String TAG = "secondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Question 2: Easy-medium");
        setContentView(R.layout.activity_second);

        initRadioGroup();
    }

    private void initRadioGroup() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.secondrg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            //@Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bpyong = (RadioButton) findViewById(R.id.pyongyang);
                RadioButton bpyeong = (RadioButton) findViewById(R.id.pyeongchang);
                RadioButton bpenn = (RadioButton) findViewById(R.id.penn);
                RadioButton bsalt = (RadioButton) findViewById(R.id.salt);
                if (bpyong.isChecked()==true){
                    secondA = "PyongYang";
//                Toast.makeText(this, "atlantic fish selected", Toast.LENGTH_SHORT ).show();
                }
                if (bpyeong.isChecked()==true){
                    secondA  ="PyeongChang";
                }if (bpenn.isChecked()==true){
                    secondA = "Pennsylvania";
                }if (bsalt.isChecked()==true){
                    secondA = "Salt Lake City";
                }
                Intent myLocalIntent = getIntent();
                Bundle myData = new Bundle();
                myData.putString("2", secondA);
                myLocalIntent.putExtras(myData);
                setResult(Activity.RESULT_OK, myLocalIntent);
                finish();
            }
        });
    }
}
