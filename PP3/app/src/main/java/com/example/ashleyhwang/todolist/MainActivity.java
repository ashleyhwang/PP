package com.example.ashleyhwang.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView todoImage = (ImageView) findViewById(R.id.todo_image);

    List<String> listtodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoImage.setImageResource(R.mipmap.checklist);
        listtodo = new ArrayList<>();
//        listtodo.add(new)

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recycler_view_id);
        RecyclierViewAdapter myAdapter = new RecyclierViewAdapter(this, listtodo);
//        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }
}
