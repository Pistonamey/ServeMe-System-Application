package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;




public class EmptyTest extends AppCompatActivity {

    public static final String EMPTYINDEX = "EMPTYINDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_test);
    }
}