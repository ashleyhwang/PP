package com.example.ashleyhwang.todolist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

    private List<Model> todolist;
    private TextToSpeech tts;
    private String temp;
    private  Context mContext;



    public CardViewDataAdapter(List<Model> phrases) {
        this.todolist = phrases;

    }

    // Create new views
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_item, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final int pos = position;
        viewHolder.tvName.setText(todolist.get(position).getItemName());

        //1. read out the item clicked
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String me = todolist.get(pos).getItemName();

                texttospeech(pos);
                Toast.makeText(
                        v.getContext(),
                        "Reading: " + me , Toast.LENGTH_LONG).show();
            }
        });

        //2. remove object from the list
        viewHolder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                String todoitem = todolist.get(pos).getItemName();
                todolist.remove(todolist.get(pos));

                notifyDataSetChanged();
                Toast.makeText(
                        v.getContext(),
                        "Deleted:" + todoitem, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {  // Return the size arraylist
        return todolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener{

        public TextView tvName;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName = (TextView) itemLayoutView.findViewById(R.id.txt_Name);

        }
    }

    public void texttospeech(int pos) {


        temp = todolist.get(pos).getItemName();
        tts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() { //need an activity context go over this in class
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                    tts.speak(temp, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }


}