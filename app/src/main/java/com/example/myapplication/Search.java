package com.example.myapplication;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;


public class Search extends AppCompatActivity implements View.OnClickListener {



    EditText et_vendSearchName;

    Button btn_search;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);




        et_vendSearchName = findViewById(R.id.et_vendSearch);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);










    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btn_search:
                vendorSearch();
                break;
        }

    }

    private void vendorSearch() {


        String vendNameString = et_vendSearchName.getText().toString().trim();



        if(vendNameString.isEmpty())
        {
            et_vendSearchName.setError("Vendor name required");
            et_vendSearchName.requestFocus();
            return;
        }


        Intent intent = new Intent(Search.this, VendorSearchResult.class);



        intent.putExtra(VendorSearchResult.SEARCH, vendNameString);

        startActivity(intent);





    }
}
