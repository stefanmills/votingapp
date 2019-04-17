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

public class Financial extends AppCompatActivity {
    private FloatingActionButton buttonFinancial;
    private TextView finan1;
    private TextView finan2;
    private String selectedFinancial;
    private CardView financial1;
    private CardView financial2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financialpage); // this shows you where you wonna direct it to
        buttonFinancial= findViewById(R.id.btFinancial);
        finan1 = findViewById(R.id.fin1text);
        finan2= findViewById(R.id.fin2text);
        financial1= findViewById(R.id.fin1);
        financial2= findViewById(R.id.fin2);



        financial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finan1.setEnabled(false);
                finan2.setEnabled(true);
                financial2.setCardBackgroundColor(Color.WHITE);
                financial1.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedFinancial = finan1.getText().toString();
            }
        });

        financial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finan2.setEnabled(false);
                finan1.setEnabled(true);
                financial1.setCardBackgroundColor(Color.WHITE);
                financial2.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedFinancial = finan2.getText().toString();
            }
        });

        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        final String selectedSecetary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);

        Toast.makeText(this, String.format("{%s} {%s}", selectedPresident, selectedSecetary), Toast.LENGTH_LONG).show();

        buttonFinancial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFinancial==null || selectedFinancial.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                } else {

                    Intent women = new Intent(getApplicationContext(), Organizer.class);
                    women.putExtra(AppConstants.selectedFinancialString, selectedFinancial);
                    women.putExtra(AppConstants.selectedSecretaryString, selectedSecetary);
                    women.putExtra(AppConstants.selectedPresidentString, selectedPresident);

                    startActivity(women);
                }

            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Financial Secretary");

    }
}



