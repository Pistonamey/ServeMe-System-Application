package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//for recycler view adapter
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{



    private Context context;

    ArrayList<Order> list;


    public MyAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Order order = list.get(position);
        holder.orderCount.setText(Integer.toString((position + 1)));

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button orderCount;
        int position;
        Order order;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            orderCount = itemView.findViewById(R.id.tvorderCount);
            orderCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = orderCount.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), VendViewReq.class);



                    intent.putExtra(VendViewReq.INDEX, index);

                    v.getContext().startActivity(intent);









                }
            });
        }
    }
}
