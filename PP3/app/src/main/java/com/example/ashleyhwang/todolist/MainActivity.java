package com.example.ashleyhwang.todolist;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

//Modified version of RecyclerItalianPhrasesFileIO from Professor Signoril
public class MainActivity extends AppCompatActivity {


    private List<Model> doList = new ArrayList<Model>();
    private Button btnSelection;
    private TextView textbox;
    private RecyclerView mRecyclerView;
    private CardViewDataAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelection = (Button) findViewById(R.id.add);
        textbox = (EditText) findViewById(R.id.textbox);

        File file = new File("data/data/com.example.ashleyhwang.todolist/test.txt");
        if (!file.exists()) {
            try { // if file doesn't exist.
                file.createNewFile();
                Toast.makeText(getApplicationContext(),
                        "file does not exist, too bad", Toast.LENGTH_LONG).show();
                Scanner scan = null;
                scan = new Scanner(getApplicationContext().getAssets().open("list1.txt"));
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    Model todo = new Model(line);
                    doList.add(todo);
                }
                PrintStream out = null;
                out = new PrintStream(openFileOutput("test.txt", MODE_PRIVATE));
                for (Model model : doList) {
                    out.println(model.getItemName());
                }
                out.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),
                        "file does not exist, but something else happened" , Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }


        else{
            try { // if file exists,
                Scanner scan = new Scanner(openFileInput("test.txt"));
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    Model todo = new Model(line);
                    doList.add(todo);
                }
            }
            catch (IOException ee) {
                Toast.makeText(getApplicationContext(),
                        "file already exists, but  something else happened" , Toast.LENGTH_LONG).show();
                ee.printStackTrace();
            }
        }

        initrecyclerview();


        btnSelection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textbox.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Enter your to-do!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Model todo = new Model(textbox.getText().toString());
                    doList.add(todo);
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),
                            "New work: \n" + textbox.getText().toString(), Toast.LENGTH_SHORT).show();
                    textbox.setText("");
                }
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        try {
            PrintStream out = new PrintStream(openFileOutput("test.txt", MODE_PRIVATE));
            for (Model model : doList) {
                out.println(model.getItemName());
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initrecyclerview(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CardViewDataAdapter(doList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
