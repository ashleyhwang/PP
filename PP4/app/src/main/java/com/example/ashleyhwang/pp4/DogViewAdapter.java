package com.example.ashleyhwang.pp4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DogViewAdapter extends RecyclerView.Adapter<DogViewAdapter.ViewHolder>{

    private List<Dog> dgList;

    private Context mContext;
    public DogViewAdapter(List<Dog> dgList){
        this.dgList=dgList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);

            tvName = (TextView) itemLayoutView.findViewById(R.id.breed);
        }

    }
    @NonNull
    @Override
    public DogViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doggo_item, null);

        //create ViewHolder;

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        mContext = parent.getContext(); //need this for later when you need another activity
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewAdapter.ViewHolder holder, int position) {
        final int pos = position;
        holder.tvName.setText(dgList.get(position).getName()); //getBreed?

//        holder.tvName.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                int me = dgList.get(pos).getId();
//
//                Dog toSend = dgList.get(pos);
//                Intent i = new Intent(mContext, dogImage.class);
//                i.putExtra("dogObject", toSend);
//                ((Activity) mContext).startActivity(i);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dgList.size();}

}
