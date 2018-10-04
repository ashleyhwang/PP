package com.example.ashleyhwang.quizapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class thirdActivity extends AppCompatActivity {
    private static final String TAG = "thirdActivity";
    private String thirdA = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Question 3: Medium");
        setContentView(R.layout.activity_third);
        initRadioGroup();
    }

    private void initRadioGroup() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.thirdrg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            //@Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bb = (RadioButton) findViewById(R.id.baloons);
                RadioButton bcab= (RadioButton) findViewById(R.id.Cabbage);
                RadioButton bv = (RadioButton) findViewById(R.id.Velvet);
                RadioButton bcc = (RadioButton) findViewById(R.id.cottoncandy);
                if (bcab.isChecked()==true){
                    thirdA = "Cabbage";
//                Toast.makeText(this, "atlantic fish selected", Toast.LENGTH_SHORT ).show();
                }
                else if (bb.isChecked()==true){
                    thirdA  ="Balloons";
                }else if (bv.isChecked()==true){
                    thirdA = "Velvet";
                }else if (bcc.isChecked()==true){
                    thirdA = "Cotton Candy";
                }

                Intent myLocalIntent = getIntent();
                Bundle myData = new Bundle();
                myData.putString("3", thirdA);
                myLocalIntent.putExtras(myData);
                setResult(Activity.RESULT_OK, myLocalIntent);
                finish();
            }
        });
    }
}
