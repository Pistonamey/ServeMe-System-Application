package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeOrder extends AppCompatActivity {

    public static final String ORDERIDSTATIC = "ORDERIDSTATIC";

    EditText et_orderDate, et_orderTime;


    Button change_update;

    private String orderID, orderDate, orderTime;

    private DatabaseReference orderDBreference;

    private String month, day, year, hour, minutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_order);

        Intent intent = getIntent();


        orderID = intent.getStringExtra(ORDERIDSTATIC);

        orderDBreference = FirebaseDatabase.getInstance().getReference();

        et_orderDate = findViewById(R.id.et_cust_date);
        et_orderTime = findViewById(R.id.et_cust_time);
        change_update = findViewById(R.id.change_update);


        change_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_order();
            }

            private void update_order() {

                orderDate = et_orderDate.getText().toString().trim();
                orderTime = et_orderTime.getText().toString().trim();

                int count = 0;



                for(int i = 0; i < orderDate.length(); i++)
                {
                    if(orderDate.charAt(i) == '/')
                    {
                        count++;
                    }
                }

                if(count != 2)
                {
                    et_orderDate.setError("Enter a valid date.");
                    et_orderDate.requestFocus();
                    return;
                }



                String [] dateParts = orderDate.split("/");
                month=dateParts[0];
                day=dateParts[1];
                year=dateParts[2];

                if(orderDate.isEmpty())
                {
                    et_orderDate.setError("Full name is required");
                    et_orderDate.requestFocus();
                    return;
                }


                if(orderTime.isEmpty())
                {
                    et_orderTime.setError("Full name is required");
                    et_orderTime.requestFocus();
                    return;
                }

                if(!isNumeric(month))
                {
                    et_orderDate.setError("Enter a valid date.");
                    et_orderDate.requestFocus();
                    return;
                }
                else {
                    int month_int = Integer.parseInt(month);
                    if (month_int < 1 || month_int > 12)
                    {
                        et_orderDate.setError("Enter a valid date.");
                        et_orderDate.requestFocus();
                        return;
                    }


                }

                if(!isNumeric(day))
                {
                    et_orderDate.setError("Enter a valid date.");
                    et_orderDate.requestFocus();
                    return;
                }
                else {
                    int day_int = Integer.parseInt(day);
                    if (day_int < 1 || day_int > 31)
                    {
                        et_orderDate.setError("Enter a valid date.");
                        et_orderDate.requestFocus();
                        return;
                    }


                }


                if(!isNumeric(year))
                {
                    et_orderDate.setError("Enter a valid date.");
                    et_orderDate.requestFocus();
                    return;
                }
                else {
                    int year_int = Integer.parseInt(year);
                    if (year_int !=22)
                    {
                        et_orderDate.setError("Enter a valid date.");
                        et_orderDate.requestFocus();
                        return;
                    }


                }



                //Test Cases for Time
                //count for checking : count
                int count2 = 0;
                for(int i = 0; i < orderTime.length(); i++)
                {
                    if(orderTime.charAt(i) == ':')
                    {
                        count2++;
                    }
                }



                if(count2 != 1)
                {
                    et_orderTime.setError("Enter a valid time.");
                    et_orderTime.requestFocus();
                    return;
                }

                String [] timeParts = orderTime.split(":");
                hour=timeParts[0];
                minutes=timeParts[1];


                if(!isNumeric(hour))
                {
                    et_orderTime.setError("Enter a valid time.");
                    et_orderTime.requestFocus();
                    return;
                }
                else {
                    int hour_int = Integer.parseInt(hour);
                    if (hour_int <0 || hour_int>24)
                    {
                        et_orderTime.setError("Enter a valid time.");
                        et_orderTime.requestFocus();
                        return;
                    }


                }


                if(!isNumeric(minutes))
                {
                    et_orderTime.setError("Enter a valid time.");
                    et_orderTime.requestFocus();
                    return;
                }
                else {
                    int min_int = Integer.parseInt(minutes);
                    if (min_int <0 || min_int>60)
                    {
                        et_orderTime.setError("Enter a valid time.");
                        et_orderTime.requestFocus();
                        return;
                    }


                }



                orderDBreference.child("Orders").child(orderID).child("servDate").setValue(orderDate, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(ChangeOrder.this, "Order failed to update in ChangeOrder", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                orderDBreference.child("Orders").child(orderID).child("servTime").setValue(orderTime, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(ChangeOrder.this, "Order failed to update in ChangeOrder", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                Toast.makeText(ChangeOrder.this, "Date and time has been updated succesfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChangeOrder.this, Cust_main_menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);














            }
        });




    }
    public static boolean isNumeric(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}