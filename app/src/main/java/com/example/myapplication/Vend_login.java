
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Vend_login extends AppCompatActivity implements View.OnClickListener {

    //for reference to current logged in vendor
    private FirebaseUser vendor;

    public static String v_s_type;
    final MediaPlayer[] mp = new MediaPlayer[1];
    CheckBox showpassword;

    //for reference to database
    private DatabaseReference reference;

    private String vendorID;



    private EditText et_email, et_pass;
    private TextView fail;
    private Button login;

    private int vendorCount = 0;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_login);

        Intent intent = getIntent();


        et_email = findViewById(R.id.log_email);
        et_pass= findViewById(R.id.log_pass);
        fail = findViewById(R.id.failed_login);

        login = (Button) findViewById(R.id.v_l_button);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        //current firebase user
        vendor = FirebaseAuth.getInstance().getCurrentUser();
        //getRefernece is for getting the name of the specific reference collection
        reference = FirebaseDatabase.getInstance().getReference("Vendor");
        //get unique id of logged in user

        //getUid caused to crash if no id available
        //vendorID = vendor.getUid();

        Button sett = (Button) findViewById(R.id.settings);

        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Vend_login.this, R.raw.but_click);
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
                Intent intent = new Intent(Vend_login.this, Vend_sett.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

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

        Button back = (Button) findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Vend_login.this, R.raw.but_click);
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
                Intent intent = new Intent(Vend_login.this, Login_main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.v_l_button:
                vendorLogin();
                break;
        }

    }


    private void vendorLogin()
    {
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
                            .orderByChild("vendorEmail")
                            .equalTo(email);

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists())
                            {
                                vendorCount++;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                           throw error.toException();

                        }
                    });*/

                    Toast.makeText(Vend_login.this, "Login Success", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Vend_login.this, Vend_main_menu.class);
                    intent.putExtra(Vend_main_menu.VENDOREMAILSTATIC, email);



                    startActivity(intent);
                    overridePendingTransition(R.anim.from_right,R.anim.to_left);




                }
                else
                {
                    fail.setText("Incorrect Email/Pasword Combination");


                }
            }
        });



    }
}