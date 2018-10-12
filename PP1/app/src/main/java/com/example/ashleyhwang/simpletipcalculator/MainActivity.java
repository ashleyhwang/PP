package com.example.ashleyhwang.simpletipcalculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText amount;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    Spinner spinner1;
    Button btn1;
    Button btn2;
    RadioGroup rgroup;
    RadioButton tip15, tip18, tip20, tip25;
    int totalPeople;
    String[] num;

    //data field
    double inputAmount = 0;
    double bp, tp, tip = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res= getResources();
        num = res.getStringArray(R.array.numbers);

        //Set the pointers
        amount = (EditText) findViewById(R.id.amount);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        rgroup=(RadioGroup) findViewById(R.id.radiogroup);
        tip15 = (RadioButton) findViewById(R.id.tip15);
        tip18 = (RadioButton) findViewById(R.id.tip18);
        tip20 = (RadioButton) findViewById(R.id.tip20);
        tip25 = (RadioButton) findViewById(R.id.tip25);
        rgroup.clearCheck();

       ArrayAdapter<CharSequence> adapter =
               ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner1.setAdapter(adapter);
       spinner1.setOnItemSelectedListener(this);


        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radiob = (RadioButton) group.findViewById(checkedId);
                if (radiob != null) {
                    switch (radiob.getId()) {
                        case R.id.tip15:
                            tip = .15;
                            break;
                        case R.id.tip18:
                            tip=.18;
                            break;
                        case R.id.tip20:
                            tip= .20;
                            break;
                        case R.id.tip25:
                            tip= .25;
                            break;
                    } changeTextView();
                } else {
                }
            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //checks whether right format of input is inserted
                if(amount.getText().toString().isEmpty()){
                    inputAmount = 0;
                }else {
                    inputAmount = Double.parseDouble(amount.getText().toString());
                }

                //checks whether radio button(tip) is selected
                if(rgroup.getCheckedRadioButtonId()== -1){
                    Toast.makeText(MainActivity.this,
                            "Don't forget to tip the server!!!", Toast.LENGTH_SHORT).show();
                    amount.getText().clear();
                } else {
                    changeTextView();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //do nothing
            }
        });
    }
    public void changeTextView(){
        bp = (inputAmount / totalPeople);
        tp = (inputAmount * tip) / totalPeople;
        txt1.setText("$" + String.format("%.2f", bp + tp));
        txt2.setText("$" + String.format("%.2f", bp));
        txt3.setText("$" + String.format("%.2f", tp));
    }


    public void onReset(View v){
        String initial="$0.00";
        rgroup.clearCheck();
        amount.setText("");
        txt1.setText(initial);
        txt2.setText(initial);
        txt3.setText(initial);
        spinner1.setSelection(0);
        Toast.makeText(MainActivity.this, "reset", Toast.LENGTH_SHORT).show();
    }


    public void onRoundUp(View v){
        double num = Double.parseDouble(txt1.getText().toString().substring(1));
        double val = Math.ceil(num);
        txt1.setText("$"+String.format("%.2f", val));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        totalPeople = Integer.parseInt(text);
        changeTextView();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }
}


