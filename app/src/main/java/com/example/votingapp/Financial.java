package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Financial extends AppCompatActivity {
    private FloatingActionButton buttonFinancial;
    private Button finan1;
    private Button finan2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financialpage); // this shows you where you wonna direct it to
        buttonFinancial= findViewById(R.id.btFinancial);
        finan1 = findViewById(R.id.financial1);
        finan2= findViewById(R.id.financial2);

        finan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finan1.setEnabled(false);
                finan2.setEnabled(true);
            }
        });

        finan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finan2.setEnabled(false);
                finan1.setEnabled(true);
            }
        });

        buttonFinancial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent women= new Intent(getApplicationContext(), WomensCommissioner.class);
                startActivity(women);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Financial Secretary");
    }
}



