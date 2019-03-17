package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecretaryPage extends AppCompatActivity {
    private FloatingActionButton buttonSecretary;
    private Button secet1;
    private Button secet2;
    private String selectedSecetary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secretarypage); // this shows you where you wonna direct it to
        buttonSecretary= findViewById(R.id.btSecretary);
        secet1 = findViewById(R.id.sece1);
        secet2= findViewById(R.id.sece2);


        secet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secet1.setEnabled(false);
                secet2.setEnabled(true);
                selectedSecetary = secet1.getText().toString();
            }
        });

        secet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secet2.setEnabled(false);
                secet1.setEnabled(true);
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
