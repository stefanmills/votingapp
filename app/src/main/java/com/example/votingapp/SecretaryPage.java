package com.example.votingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecretaryPage extends AppCompatActivity {
    private FloatingActionButton buttonSecretary;
    private TextView secet1;
    private TextView secet2;
    private String selectedSecetary;
    private CardView sec1;
    private CardView sec2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secretarypage); // this shows you where you wonna direct it to
        buttonSecretary= findViewById(R.id.btSecretary);
        secet1 = findViewById(R.id.B1);
        secet2= findViewById(R.id.B2);
        sec1 = findViewById(R.id.sec1);
        sec2 = findViewById(R.id.sec2);


        sec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secet1.setEnabled(false);
                secet2.setEnabled(true);
                sec2.setCardBackgroundColor(Color.WHITE);
                sec1.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedSecetary = secet1.getText().toString();
            }
        });

        sec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secet2.setEnabled(false);
                secet1.setEnabled(true);
                sec1.setCardBackgroundColor(Color.WHITE);
                sec2.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedSecetary = secet2.getText().toString();
            }
        });

        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);

        Toast.makeText(this, String.format("{%s}", selectedPresident), Toast.LENGTH_LONG).show();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Secretary");

        buttonSecretary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSecetary==null || selectedSecetary.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                }else {
                    Intent financial= new Intent(getApplicationContext(), Financial.class);
                    financial.putExtra(AppConstants.selectedSecretaryString, selectedSecetary);
                    financial.putExtra(AppConstants.selectedPresidentString, selectedPresident);
                    startActivity(financial);
                }

            }
        });
}
}
