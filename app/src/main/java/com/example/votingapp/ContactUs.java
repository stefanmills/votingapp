package com.example.votingapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contactus);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Contact Us");
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));


    }
}
