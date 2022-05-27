package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PstVendAdapter extends RecyclerView.Adapter<PstVendAdapter.PstViewHolder> {





    Context context;
    ArrayList<Order> pstVendList;

    public PstVendAdapter(Context context, ArrayList<Order> pstVendList) {
        this.context = context;
        this.pstVendList = pstVendList;
    }

    @NonNull
    @Override
    public PstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.vendpstorder, parent, false);
        return new PstViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PstViewHolder holder, int position) {

        Order order = pstVendList.get(position);
        holder.tvpstVendCount.setText(Integer.toString((position + 1)));




    }

    @Override
    public int getItemCount() {
        return pstVendList.size();
    }

    public static class PstViewHolder extends RecyclerView.ViewHolder{


        Button tvpstVendCount;


        public PstViewHolder(@NonNull View itemView) {
            super(itemView);

            tvpstVendCount = itemView.findViewById(R.id.tvpstVendCount);
            tvpstVendCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = tvpstVendCount.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), PstVendView.class);



                    intent.putExtra(PstVendView.PSTVENDINDEX, index);

                    v.getContext().startActivity(intent);

                }
            });


        }
    }

}
