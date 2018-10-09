package com.example.ashleyhwang.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView todoImage = (ImageView) findViewById(R.id.todo_image);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoImage.setImageResource(R.mipmap.checklist);
    }
}
