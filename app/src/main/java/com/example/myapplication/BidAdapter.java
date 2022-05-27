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

public class BidAdapter extends RecyclerView.Adapter<BidAdapter.BidViewHolder> {


    Context context;

    ArrayList<Order> bidList;


    public BidAdapter(Context context, ArrayList<Order> bidList) {
        this.context = context;
        this.bidList = bidList;
    }

    @NonNull
    @Override
    public BidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bid, parent, false);

        return new BidViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BidViewHolder holder, int position) {


        Order order = bidList.get(position);
        holder.bidCount.setText(Integer.toString((position + 1)));


    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    public static class BidViewHolder extends RecyclerView.ViewHolder{


        Button bidCount;
        int position;
        Order order;


        public BidViewHolder(@NonNull View itemView) {
            super(itemView);

            bidCount = itemView.findViewById(R.id.tvbidCount);
            bidCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = bidCount.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), CustViewReq.class);



                    intent.putExtra(CustViewReq.CUSTOMERINDEX, index);

                    v.getContext().startActivity(intent);

                }
            });




        }
    }
}
