package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ViewResultPage extends AppCompatActivity {
    private Button buttonOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewresultpage); // this shows you where you wonna direct it to
        buttonOk= findViewById(R.id.btOK);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // but before the next line it should send the votes to the xerver
                Intent vote= new Intent(getApplicationContext(), VotePage.class);
                startActivity(vote);
            }
        });

        ActionBar actionBar = getSupportActionBar();
       actionBar.setTitle("View Results");
            //}
       // });

        //ActionBar actionBar= getSupportActionBar(); //this is for "back"
        // actionBar.setTitle("STEFAN"); //THIS IS JUST TO CHANGE THE TOP NAME


    }
}