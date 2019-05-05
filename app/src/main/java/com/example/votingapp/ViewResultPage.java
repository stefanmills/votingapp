package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.iwgang.countdownview.CountdownView;

public class ViewResultPage extends AppCompatActivity {
    private Button buttonOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewresultpage); // this shows you where you wonna direct it to
        buttonOk= findViewById(R.id.btOK);

        CountdownView countDownTimer= (CountdownView) findViewById(R.id.countdownview);
        countDownTimer.start(86400);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // but before the next line it should send the votes to the xerver
                Intent vote= new Intent(getApplicationContext(), VotePage.class);
                startActivity(vote);
            }
        });

       // ActionBar actionBar = getSupportActionBar();
       setTitle("View Results");


       //}
       // });

        //ActionBar actionBar= getSupportActionBar(); //this is for "back"
        // actionBar.setTitle("STEFAN"); //THIS IS JUST TO CHANGE THE TOP NAME


    }
}