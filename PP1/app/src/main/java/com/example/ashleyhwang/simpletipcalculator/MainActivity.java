package com.example.ashleyhwang.simpletipcalculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView txtMsg;
    String[] num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res= getResources();
        num= res.getStringArray(R.array.numbers);

       spinner = (Spinner) findViewById(R.id.spinner);

       ArrayAdapter<CharSequence> adapter =
               ArrayAdapter.createFromResource(this,R.array.numbers, R.layout.support_simple_spinner_dropdown_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> arg0, View v, int select, long arg3) {
               // echo on the textbox the user's selection
               txtMsg.setText(num[select]);
           }

           @Override
           public void onNothingSelected(AdapterView<?> arg0){
               //do nothing;
           }
       });


    }
}
