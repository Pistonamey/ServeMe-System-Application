package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class RateOrder extends AppCompatActivity implements View.OnClickListener {

    public static final String ORDERIDSTATIC = "ORDERIDSTATIC";
    public static final String VENDERMAILSTATIC = "VENDEMAILSTATIC";





    private String orderID, vendorEmail, vendorID;

    int ratingProvided;

    private int oneStarCount, twoStarCount, threeStarCount, fourStarCount, fiveStarCount;
    RatingBar ratingStars;
    Button btn_submitRating;

    private DatabaseReference rootRef;



    private DatabaseReference vendorDBreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_order);



        Intent intent = getIntent();
        orderID = intent.getStringExtra(ORDERIDSTATIC);
        vendorEmail = intent.getStringExtra(VENDERMAILSTATIC);

        ratingStars = findViewById(R.id.ratingBar);

        btn_submitRating = findViewById(R.id.btn_submitRating);
        btn_submitRating.setOnClickListener(this);

        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor");

        rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Vendor").orderByChild("vendorEmail").equalTo(vendorEmail);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    vendorID = ds.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };



        query.addListenerForSingleValueEvent(valueEventListener);


        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor").child(vendorID);


        vendorDBreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                oneStarCount = (int) snapshot.child("oneStarCount").getValue();
                twoStarCount = (int) snapshot.child("twoStarCount").getValue();
                threeStarCount = (int) snapshot.child("threeStarCount").getValue();
                fourStarCount = (int) snapshot.child("fourStarCount").getValue();
                fiveStarCount = (int) snapshot.child("fiveStarCount").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {



                ratingProvided = (int) rating;








            }
        });




        //TODO WE HAVE VENDOR'S ID, have to open up database get the current ONE STAR COUNT
        //TWO STARCOUNt, THREE STAR COUNT, FOUR STAR COUNT, and 5 star count









    }

    @Override
    public void onClick(View v) {


        switch(v.getId())
        {
            case R.id.btn_submitRating:
                gatherRating();
                break;
        }

    }



    private void gatherRating() {



        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor");



        if(ratingProvided == 1)
        {



            vendorDBreference.child("Vendor").child(orderID).child("oneStarCount").setValue(oneStarCount + 1, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if(error == null)
                    {

                    }
                    else
                    {
                        Toast.makeText(RateOrder.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                    }
                }
            });





        }
        else if(ratingProvided == 2)
        {

            vendorDBreference.child("Vendor").child(orderID).child("twoStarCount").setValue(twoStarCount + 1, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if(error == null)
                    {

                    }
                    else
                    {
                        Toast.makeText(RateOrder.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                    }
                }
            });



        }

        else if(ratingProvided == 3)
        {

            vendorDBreference.child("Vendor").child(orderID).child("threeStarCount").setValue(threeStarCount + 1, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if(error == null)
                    {

                    }
                    else
                    {
                        Toast.makeText(RateOrder.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        else if(ratingProvided == 4)
        {
            vendorDBreference.child("Vendor").child(orderID).child("fourStarCount").setValue(fourStarCount + 1, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if(error == null)
                    {

                    }
                    else
                    {
                        Toast.makeText(RateOrder.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
        else if (ratingProvided == 5)
        {
            vendorDBreference.child("Vendor").child(orderID).child("fiveStarCount").setValue(fiveStarCount + 1, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if(error == null)
                    {

                    }
                    else
                    {
                        Toast.makeText(RateOrder.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }




    }
}


