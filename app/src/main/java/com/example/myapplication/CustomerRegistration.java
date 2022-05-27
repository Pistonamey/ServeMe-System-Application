package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class CustomerRegistration extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText et_name, et_email, et_number, et_password, et_address, et_confirm_pass;
    Button btn_register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_reg);


        mAuth = FirebaseAuth.getInstance();
        et_name = findViewById(R.id.reg_name);
        et_email = findViewById(R.id.reg_email);
        et_number = findViewById(R.id.reg_ph);
        et_address = findViewById(R.id.reg_add);
        et_password = findViewById(R.id.reg_pass);
        et_confirm_pass = findViewById(R.id.reg_pass_c);
        btn_register = (Button) findViewById(R.id.c_r_button);
        btn_register.setOnClickListener(this);
    }

    //onClick for btn_register
    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.c_r_button:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String number = et_number.getText().toString().trim();
        String address = et_address.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String confirm_pass = et_confirm_pass.getText().toString().trim();

        if(name.isEmpty())
        {
            et_name.setError("Full name is required");
            et_name.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if(number.isEmpty())
        {
            et_number.setError("Number is required");
            et_number.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            et_password.setError("Password is required");
            et_password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            et_email.setError("Please provide valid email");
            et_email.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            et_password.setError("Password must be at least 6 characters");
            et_password.requestFocus();
            return;
        }

        if(confirm_pass.isEmpty())
        {
            et_confirm_pass.setError("Please confirm your password");
            et_confirm_pass.requestFocus();
            return;
        }

        if(!confirm_pass.matches(password))
        {
            et_confirm_pass.setError("Password and confirm password mismatch.");
            et_confirm_pass.requestFocus();
            return;
        }

        if(address.isEmpty())
        {
            et_address.setError("Please enter an address");
            et_address.requestFocus();
            return;
        }




        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Customer customer = new Customer(name, email, number, address, password, "Customer");

                            //within paranthesis returns id for registered user
                            FirebaseDatabase.getInstance().getReference("Customers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(CustomerRegistration.this, "User has been registered succesfully", Toast.LENGTH_LONG).show();

                                        //redirect to Login layout
                                        Intent intent = new Intent(CustomerRegistration.this, Cust_Login.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.from_right,R.anim.to_left);

                                    }
                                    else
                                    {
                                        Toast.makeText(CustomerRegistration.this, "Failed to register. Try again.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(CustomerRegistration.this, "Failed to register. Try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }


}