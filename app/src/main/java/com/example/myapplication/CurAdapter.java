package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CurAdapter extends RecyclerView.Adapter<CurAdapter.CurViewHolder>{


    Context context;

    ArrayList<Order> curList;


    public CurAdapter(Context context, ArrayList<Order> curList) {
        this.context = context;
        this.curList = curList;
    }

    @NonNull
    @Override
    public CurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.current, parent, false);
        return new CurViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurViewHolder holder, int position) {


        Order order = curList.get(position);
        holder.curCount.setText(Integer.toString((position + 1)));


    }

    @Override
    public int getItemCount() {
        return curList.size();
    }

    public static class CurViewHolder extends RecyclerView.ViewHolder{


        Button curCount;


        public CurViewHolder(@NonNull View itemView) {
            super(itemView);

            curCount = itemView.findViewById(R.id.tvcurrentCount);
            curCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = curCount.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), CurViewReq.class);



                    intent.putExtra(CurViewReq.CURRENTINDEX, index);

                    v.getContext().startActivity(intent);


                }
            });


        }
    }











}
