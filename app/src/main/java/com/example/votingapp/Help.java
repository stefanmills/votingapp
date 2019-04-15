package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Help extends AppCompatActivity {

    private CardView buttonFAQ;
    private CardView buttonContact;
    private CardView buttonTerms;
    private CardView buttonInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Help");

        buttonInfo = findViewById(R.id.appinfo);
        buttonContact= findViewById(R.id.contact);

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appinfo= new Intent(getApplicationContext(), AppInfo.class);
                startActivity(appinfo);
            }
        });

        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contact= new Intent(getApplicationContext(), ContactUs.class);
                startActivity(contact);
            }
        });



    }
}
