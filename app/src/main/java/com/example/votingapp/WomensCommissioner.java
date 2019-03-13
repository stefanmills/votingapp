package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WomensCommissioner extends AppCompatActivity {
    private FloatingActionButton buttonWomen;
    private Button wocom1;
    private Button wocom2;
    private String selectedWomen;
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
                selectedWomen = wocom1.getText().toString();
            }
        });

        wocom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wocom2.setEnabled(false);
                wocom1.setEnabled(true);
                selectedWomen = wocom2.getText().toString();
            }
        });

        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        final String selectedSecetary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);
        final String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);

        Toast.makeText(this, String.format("{%s} {%s} {%s}", selectedFinancial, selectedPresident, selectedSecetary), Toast.LENGTH_SHORT).show();

        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent women= new Intent(getApplicationContext(), SelectedVotes.class);
                women.putExtra(AppConstants.selectedFinancialString, selectedFinancial);
                women.putExtra(AppConstants.selectedSecretaryString, selectedSecetary);
                women.putExtra(AppConstants.selectedPresidentString, selectedPresident);
                women.putExtra(AppConstants.selectedWocomString, selectedWomen);

                startActivity(women);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Women's Commissioner");
    }
}
