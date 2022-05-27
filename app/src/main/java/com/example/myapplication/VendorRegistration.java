package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class VendorRegistration extends AppCompatActivity {






    EditText et_ven_name, et_ven_email, et_ven_num, et_ven_address, et_ven_pass, et_ven_confirmPass;
    private String vendorName, vendorEmail, vendorNumber, vendorAddress, vendorPassword, vendorConfirmPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);

        Intent intent = getIntent();



        et_ven_name = (EditText) findViewById(R.id.v_reg_name);
        et_ven_email = (EditText) findViewById(R.id.v_reg_email);
        et_ven_num = (EditText) findViewById(R.id.v_reg_ph);
        et_ven_address = (EditText) findViewById(R.id.v_reg_add);
        et_ven_pass = (EditText) findViewById(R.id.v_reg_pass);
        et_ven_confirmPass = (EditText) findViewById(R.id.v_reg_pass_c);


        Button sett = (Button) findViewById(R.id.settings);
        Button v_create = (Button) findViewById(R.id.v_r_button);

        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(VendorRegistration.this, Vend_sett.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        v_create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                vendorName = et_ven_name.getText().toString().trim();
                vendorEmail = et_ven_email.getText().toString().trim();
                vendorNumber = et_ven_num.getText().toString().trim();
                vendorAddress = et_ven_address.getText().toString().trim();
                vendorPassword = et_ven_pass.getText().toString().trim();
                vendorConfirmPass = et_ven_confirmPass.getText().toString().trim();


                if(vendorName.isEmpty())
                {
                    et_ven_name.setError("Full name is required");
                    et_ven_name.requestFocus();
                    return;
                }

                if(vendorNumber.isEmpty())
                {
                    et_ven_num.setError("Number is required");
                    et_ven_num.requestFocus();
                    return;
                }

                if(vendorPassword.isEmpty())
                {
                    et_ven_pass.setError("Password is required");
                    et_ven_pass.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(vendorEmail).matches())
                {
                    et_ven_email.setError("Please provide valid email");
                    et_ven_email.requestFocus();
                    return;
                }

                if(vendorPassword.length() < 6)
                {
                    et_ven_pass.setError("Password must be at least 6 characters");
                    et_ven_pass.requestFocus();
                    return;
                }

                if(vendorConfirmPass.isEmpty())
                {
                    et_ven_confirmPass.setError("Please confirm your password");
                    et_ven_confirmPass.requestFocus();
                    return;
                }

                if(!vendorConfirmPass.matches(vendorPassword ))
                {
                    et_ven_confirmPass.setError("Password and confirm password mismatch.");
                    et_ven_confirmPass.requestFocus();
                    return;
                }

                if(vendorAddress.isEmpty())
                {
                    et_ven_address.setError("Please enter an address");
                    et_ven_address.requestFocus();
                    return;
                }


                Intent intent = new Intent(VendorRegistration.this, select_serv.class);



                intent.putExtra(select_serv.NAME, vendorName);
                intent.putExtra(select_serv.EMAIL, vendorEmail);
                intent.putExtra(select_serv.PHONE, vendorNumber);
                intent.putExtra(select_serv.ADDRESS, vendorAddress);
                intent.putExtra(select_serv.PASSWORD, vendorPassword);

                startActivity(intent);
            }

        });




    }



}
