package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Cust_Login extends AppCompatActivity implements View.OnClickListener {

    private EditText et_email, et_pass;
    private TextView fail,fp_button;
    private Button btn_login;
    CheckBox showpassword;

    private FirebaseAuth mAuth;

    private int customerCount;
    final MediaPlayer[] mp = new MediaPlayer[1];

    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_login);


        Intent intent = getIntent();
        et_email = findViewById(R.id.log_email);
        et_pass = findViewById(R.id.log_pass);
        fail = findViewById(R.id.failed_login);
        Button sett = (Button) findViewById(R.id.settings);
        fp_button = (TextView) findViewById(R.id.fgt_pass);
        showpassword = findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    et_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else{
                    et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });



        btn_login=(Button) findViewById(R.id.c_r_button);
        btn_login.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("Customers");

        fp_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Cust_Login.this, forgot_password.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });


        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Cust_Login.this, R.raw.but_click);
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
                Intent intent = new Intent(Cust_Login.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back = (Button) findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Cust_Login.this, R.raw.but_click);
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
                Intent intent = new Intent(Cust_Login.this, Login_main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });



    }

    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.c_r_button:
                userLogin();
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Cust_Login.this, R.raw.but_click);
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
                break;
        }
    }

    private void userLogin() {

        String email = et_email.getText().toString().trim();
        String password = et_pass.getText().toString().trim();

        //validations
        if(email.isEmpty())
        {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Please enter a valid email");
            et_email.requestFocus();
            return;
        }


        if(email.isEmpty())
        {
            et_pass.setError("Password is required");
            et_pass.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            et_pass.setError("Password must be at least 6 characters.");
            et_pass.requestFocus();
            return;
        }

        //sign in the user - only upon completion was there a succesfull login

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    /*Query query = reference
                            .orderByChild("email")
                            .equalTo(email);

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists())
                            {
                                customerCount++;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            throw error.toException();

                        }
                    });*/



                    //redirect to home page
                    Toast.makeText(Cust_Login.this, "Login Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Cust_Login.this, Cust_main_menu.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.from_right,R.anim.to_left);






                }
                else
                {
                    //Toast.makeText(Cust_Login.this, "Failed to login. Please check your credentials.", Toast.LENGTH_LONG).show();

                    fail.setText("Incorrect Email/Pasword Combination");
                }
            }
        });




    }
}