package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {





    Context context;

    ArrayList<Vendor> vendorList;

    public SearchAdapter(Context context, ArrayList<Vendor> vendorList) {
        this.context = context;
        this.vendorList = vendorList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.searchitem, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        Vendor vendor = vendorList.get(position);
        holder.vendName.setText(vendor.getVendorName());
        holder.vendEmail.setText(vendor.getVendorEmail());
        holder.vendType.setText(vendor.getVendorServiceType());
        holder.vendNumber.setText(vendor.getVendorNumber());




    }

    @Override
    public int getItemCount() {
        return vendorList.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView vendName, vendEmail, vendType, vendNumber;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            vendName = itemView.findViewById(R.id.tvvendfirstName);
            vendEmail = itemView.findViewById(R.id.tvvendemail);
            vendType = itemView.findViewById(R.id.tvvendserviceType);
            vendNumber = itemView.findViewById(R.id.tvvendphoneNumber);
            //delete all this and in searchitem.xml set it to TextView again



        }
    }
}
