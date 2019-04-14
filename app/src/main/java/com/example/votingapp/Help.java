package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {

    private Button buttonFAQ;
    private Button buttonContact;
    private Button buttonTerms;
    private Button buttonInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Help");

        buttonInfo = findViewById(R.id.appinfo);

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appinfo= new Intent(getApplicationContext(), AppInfo.class);
                startActivity(appinfo);
            }
        });





    }
}
