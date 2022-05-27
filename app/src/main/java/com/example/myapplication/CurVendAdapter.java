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

public class CurVendAdapter extends RecyclerView.Adapter<CurVendAdapter.CurVendHolder> {


    Context context;


    ArrayList<Order> curVendList;


    public CurVendAdapter(Context context, ArrayList<Order> curVendList) {
        this.context = context;
        this.curVendList = curVendList;
    }

    @NonNull
    @Override
    public CurVendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.curvendxml, parent, false);
        return new CurVendHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurVendHolder holder, int position) {

        Order order = curVendList.get(position);
        holder.curVendCount.setText(Integer.toString((position + 1)));

    }

    @Override
    public int getItemCount() {
        return curVendList.size();
    }

    public static class CurVendHolder extends RecyclerView.ViewHolder{


        Button curVendCount;


        public CurVendHolder(@NonNull View itemView) {

            super(itemView);

            curVendCount = itemView.findViewById(R.id.tvcurvendCount);
            curVendCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = curVendCount.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), CurVendViewReq.class);



                    intent.putExtra(CurVendViewReq.CURVENDINDEX, index);

                    v.getContext().startActivity(intent);
                }
            });

        }

    }








}
