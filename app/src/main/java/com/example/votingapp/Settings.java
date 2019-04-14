package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Settings extends AppCompatActivity {
    private Button buttonsetpro;
    private Button buttonsethelp;
    private Button buttonsetacc;
    private Button buttonsetshare;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
        buttonsetpro = findViewById(R.id.namebut);
        buttonsetshare = findViewById(R.id.invite);
        buttonsetacc = findViewById(R.id.accounts);
        buttonsethelp = findViewById(R.id.sethelp);

        buttonsetpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account= new Intent(getApplication(),Profile.class);
                startActivity(account);

            }
        });

        buttonsethelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help= new Intent(getApplicationContext(),Help.class);
                startActivity(help);
            }
        });

        buttonsetshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share= new Intent(getApplicationContext(), Share.class);
                startActivity(share);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");




    }
}
