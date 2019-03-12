package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WomensCommissioner extends AppCompatActivity {
    private FloatingActionButton buttonWomen;
    private Button wocom1;
    private Button wocom2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womenscommissioner); // this shows you where you wonna direct it to
        buttonWomen= findViewById(R.id.btWomen);
        wocom1 = findViewById(R.id.woman1);
        wocom2= findViewById(R.id.woman2);



        wocom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wocom1.setEnabled(false);
                wocom2.setEnabled(true);
            }
        });

        wocom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wocom2.setEnabled(false);
                wocom1.setEnabled(true);
            }
        });
        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent women= new Intent(getApplicationContext(), SelectedVotes.class);
                startActivity(women);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Women's Commissioner");
    }
}
