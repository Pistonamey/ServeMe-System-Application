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

public class PstOrderAdapter extends RecyclerView.Adapter<PstOrderAdapter.PstViewHolder>{

    Context context;
    ArrayList<Order> pstOrderList;


    public PstOrderAdapter(Context context, ArrayList<Order> pstOrderList) {
        this.context = context;
        this.pstOrderList = pstOrderList;
    }

    @NonNull
    @Override
    public PstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pstorder, parent, false);
        return new PstViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull PstViewHolder holder, int position) {

        Order order = pstOrderList.get(position);
        holder.pstOrderCount.setText(Integer.toString((position + 1)));



    }

    @Override
    public int getItemCount() {
        return pstOrderList.size();
    }

    public static class PstViewHolder extends RecyclerView.ViewHolder{

        Button pstOrderCount;


        public PstViewHolder(@NonNull View itemView) {
            super(itemView);


            pstOrderCount = itemView.findViewById(R.id.tvpstorderCount);
            pstOrderCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = pstOrderCount.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), PstOrderView.class);



                    intent.putExtra(PstOrderView.PSTINDEX, index);

                    v.getContext().startActivity(intent);

                }
            });



        }
    }
}
