package com.example.votingapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import cn.iwgang.countdownview.CountdownView;

public class ViewResultPage extends AppCompatActivity {
    private Button buttonOk;
    private final String TAG = this.getClass().getSimpleName();
    private CardView results;
    private TextView resulttxt;
    private  String displayresults;
    private PrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewresultpage); // this shows you where you wonna direct it to
        buttonOk= findViewById(R.id.btOK);
        results=findViewById(R.id.resultscard);


        prefsManager = new PrefsManager(this);
        resulttxt=findViewById(R.id.resultdisplay);
        displayresults=prefsManager.getResults();
        resulttxt.setText(displayresults);

        CountdownView countDownTimer= (CountdownView) findViewById(R.id.countdownview);
        countDownTimer.start(32400000);

        final String link = "http://smsvotingpro.ga/androidResultsApi.php";

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // but before the next line it should send the votes to the xerver
                Intent vote= new Intent(getApplicationContext(), VotePage.class);
                startActivity(vote);
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.post(link)

                        .setTag(TAG)
                        .setPriority(Priority.IMMEDIATE)
                        .build()
                        .getAsString(new StringRequestListener() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Response is: " + response);
                       resulttxt.setText(response);
    prefsManager.setResults(response);
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });
        ActionBar actionBar = getSupportActionBar();
       setTitle("View Results");
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));





    }
}