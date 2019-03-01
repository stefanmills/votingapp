package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Financial extends AppCompatActivity {
    private Button buttonFinancial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financialpage); // this shows you where you wonna direct it to
        buttonFinancial= findViewById(R.id.btFinancial);

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



