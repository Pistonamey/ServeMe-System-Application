package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class jb_des extends AppCompatActivity implements View.OnClickListener {

    private NotificationManagerCompat notificationManager;
    //for reference to current logged in user
    private FirebaseUser user;
    final MediaPlayer[] mp = new MediaPlayer[1];

    private FirebaseAuth mAuth;

    //for database reference
    private DatabaseReference reference;

    private DatabaseReference orderDbRef;

    //for id of currently logged in user
    private String userID, email;


    DatabaseReference database;

    private String uniqueID;




    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String S_TYPE = "S_TYPE";

    private TextView dateText, timeText, typeText, descView;
    private EditText jobDescText;
    private String dateString, timeString, typeString, jobDescString;
    private Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jb_des);

        notificationManager = NotificationManagerCompat.from(this);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();

        btn_submit = (Button) findViewById(R.id.submit_desc);
        btn_submit.setOnClickListener(this);

        dateText = findViewById(R.id.dateView);
        timeText = findViewById(R.id.timeView);
        typeText = findViewById(R.id.s_typeView);

        jobDescText = findViewById(R.id.desc);
        descView = findViewById(R.id.descView);





        dateString = intent.getStringExtra(DATE);
        timeString = intent.getStringExtra(TIME);
        typeString = intent.getStringExtra(S_TYPE);

        //dateText.setText(dateString);
        //timeText.setText(timeString);
        //typeText.setText(typeString);


        //FireBase user code
        user = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Customers");
        orderDbRef = FirebaseDatabase.getInstance().getReference().child("Orders");


        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(jb_des.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(jb_des.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back = (Button) findViewById(R.id.go_back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(jb_des.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(jb_des.this, date_time.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });



        userID = user.getUid();


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customerProfile = snapshot.getValue(Customer.class);
                if(customerProfile != null)
                {
                    //customer's email
                    email = customerProfile.email;




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(jb_des.this, "Something wrong happened", Toast.LENGTH_LONG).show();

            }
        });






    }




    public void onClick(View v) {
        if(MainActivity.so) {
            mp[0] = MediaPlayer.create(jb_des.this, R.raw.but_click);
            mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
        switch(v.getId())
        {

            case R.id.submit_desc:
                addOrder();
                break;
            //add other case for settings

        }
    }





    private void addOrder()
    {
        jobDescString = jobDescText.getText().toString().trim();


        //TestCases
        if(jobDescString.isEmpty())
        {
            jobDescText.setError("A job desription is required");
            jobDescText.requestFocus();
            return;
        }

        if(jobDescString.length() < 10)
        {
            jobDescText.setError("Job description must be at least 10 characters.");
            jobDescText.requestFocus();
            return;
        }

        if(jobDescString.length() > 300)
        {
            jobDescText.setError("Job description can't surpass 300 characters");
            jobDescText.requestFocus();
            return;
        }









        //descView.setText(jobDescString);






        //here we will have code to addOrder
        //strings to add to orders database are: email, jobDescString, dateString, timeString, typeString
        //apply what we did in customer registration but with orders and make sure to have a new instance "Orders"


        //initially vendEmail, customerAccepted, and vendorAccepted basically have no values
        Order order = new Order(email, jobDescString, dateString, timeString, typeString, "", "","", "", "","","","","","", false, false);

        database = FirebaseDatabase.getInstance().getReference("Orders");

        orderDbRef.push().setValue(order);
        Toast.makeText(jb_des.this, "Order inserted", Toast.LENGTH_SHORT).show();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);
                    uniqueID = dataSnapshot.getKey();

                    database.child(uniqueID).child("orderID").setValue(uniqueID, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if(error == null)
                            {

                            }
                            else
                            {
                                Toast.makeText(jb_des.this, "Order failed to update", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String title = "ServeMe System";
        String message = "Your request has been placed!!";

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_D)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1,notification);

        Intent intent = new Intent(jb_des.this, req_completed.class);

        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);

    }


}












