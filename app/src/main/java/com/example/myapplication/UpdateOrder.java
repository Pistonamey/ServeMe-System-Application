package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateOrder extends AppCompatActivity implements View.OnClickListener {




    private DatabaseReference orderDBreference;


    EditText et_orderID, et_newDesc;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);


        et_orderID = findViewById(R.id.et_orderID);
        et_newDesc = findViewById(R.id.et_newDesc);
        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

        orderDBreference = FirebaseDatabase.getInstance().getReference();









    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_update:
                updateOrder();
                break;
        }
    }

    private void updateOrder() {


        String orderID = et_orderID.getText().toString().trim();
        String newDesc = et_newDesc.getText().toString().trim();

        if(orderID.isEmpty())
        {
            et_orderID.setError("Full name is required");
            et_orderID.requestFocus();
            return;
        }

        if(newDesc.isEmpty())
        {
            et_newDesc.setError("Email is required");
            et_newDesc.requestFocus();
            return;
        }

        //go to firebase documentation for reading data serValue to set how to fully update with Orders class all values.
        orderDBreference.child("Orders").child(orderID).child("vendorAccepted").setValue(true, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {
                    Toast.makeText(UpdateOrder.this, "Order has been updated succesfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(UpdateOrder.this, "Order failed to update", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}