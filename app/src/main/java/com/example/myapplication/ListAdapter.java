package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Customer> customerList;

    public ListAdapter(Activity mContext, List<Customer> customerList){
        super(mContext, R.layout.list_customer, customerList);
        this.mContext = mContext;
        this.customerList = customerList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_customer,null, true);

        TextView tvName = listItemView.findViewById(R.id.tvName);

        Customer customers = customerList.get(position);

        tvName.setText(customers.getName());

        return listItemView;
    }
}
