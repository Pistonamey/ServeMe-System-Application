package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.LuhnChecksumValidator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class PaymentPage extends AppCompatActivity implements View.OnClickListener {

    public static final String PAYMENTPAGETOTAL = "PAYMENTPAGETOTAL";
    public static final String ORDERIDSTATIC = "ORDERIDSTATIC";



    EditText et_cardNumber, et_cardHolderName, et_cardExpDate, et_CVV;

    TextView tv_serviceTotalText, tv_serviceTotalTextView;

    Button btn_submitPayent;

    private DatabaseReference orderDBreference;








    private String paymentTotal, orderID;

    //for payment you need the total



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);


        Intent intent = getIntent();

        orderDBreference = FirebaseDatabase.getInstance().getReference();

        paymentTotal = intent.getStringExtra(PAYMENTPAGETOTAL);
        orderID = intent.getStringExtra(ORDERIDSTATIC);





        et_cardNumber = findViewById(R.id.et_cardNumber);
        et_cardHolderName = findViewById(R.id.et_cardHolderName);
        et_cardExpDate = findViewById(R.id.et_cardExpDate);
        et_CVV = findViewById(R.id.et_CVV);

        tv_serviceTotalText = findViewById(R.id.tv_serviceTotalText);
        tv_serviceTotalTextView = findViewById(R.id.tv_serviceTotalTextView);
        tv_serviceTotalTextView.setText(paymentTotal);

        btn_submitPayent = findViewById(R.id.btn_submitPayent);
        btn_submitPayent.setOnClickListener(this);




















    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_submitPayent:
                submitPayment();
                break;
        }
    }

    private void submitPayment() {


        String cardNumber = et_cardNumber.getText().toString().trim();
        String cardHolderName = et_cardHolderName.getText().toString().trim();
        String cardExpDate = et_cardExpDate.getText().toString().trim();
        String CVV = et_CVV.getText().toString().trim();
        if(cardNumber.isEmpty())
        {
            et_cardNumber.setError("Card number is required");
            et_cardNumber.requestFocus();
            return;
        }

        if(cardHolderName.isEmpty())
        {
            et_cardHolderName.setError("Full name is required");
            et_cardHolderName.requestFocus();
            return;
        }

        if(cardExpDate.isEmpty())
        {
            et_cardExpDate.setError("Card's expiration date is required");
            et_cardExpDate.requestFocus();
            return;
        }

        if(CVV.isEmpty())
        {
            et_CVV.setError("Password is required");
            et_CVV.requestFocus();
            return;
        }


        if(CVV.length() != 3)
        {
            et_CVV.setError("CVV must be 3 characters");
            et_CVV.requestFocus();
            return;
        }

        if(cardNumber.length() != 16)
        {
            et_cardNumber.setError("Card number must be 16 digits");
            et_cardNumber.requestFocus();
            return;

        }


        if(!isNumeric(cardNumber))
        {
            et_cardNumber.setError("Card number must only contain digits");
            et_cardNumber.requestFocus();
            return;
        }


        if(!isLuhns(cardNumber))
        {
            et_cardNumber.setError("Card number is invalid");
            et_cardNumber.requestFocus();
            return;
        }


        int count2 = 0;
        int count3 = 0;
        for(int i = 0; i < cardExpDate.length(); i++)
        {
            if(cardExpDate.charAt(i) == '/')
            {
                count2++;
            }
            else
            {
                if(Character.isDigit(cardNumber.charAt(i)))
                {
                    count3++;
                }
            }
        }



        if(count2 != 1 || count3 != 4)
        {
            et_cardExpDate.setError("Enter expiration date of format MM/YY.");
            et_cardExpDate.requestFocus();
            return;
        }


        orderDBreference.child("Orders").child(orderID).child("orderStatus").setValue("Complete", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(PaymentPage.this, "Order failed to update", Toast.LENGTH_LONG).show();
                }
            }
        });





        Toast.makeText(PaymentPage.this, "Transaction completed.", Toast.LENGTH_LONG).show();

        //redirect to Login layout
        Intent intent = new Intent(PaymentPage.this, Cust_main_menu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);









    }




    //determine if cardNumber passes Luhn algorithim
    public static boolean isLuhns(String cardNumber)
    {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }


    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }


}