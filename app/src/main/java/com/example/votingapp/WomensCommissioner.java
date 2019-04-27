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

public class WomensCommissioner extends AppCompatActivity {
    private FloatingActionButton buttonWomen;
    private TextView wocom1;
    private TextView wocom2;
    private String selectedWomen;
    private CardView woman1card;
    private CardView woman2card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womenscommissioner); // this shows you where you wonna direct it to
        buttonWomen= findViewById(R.id.btWomen);
        wocom1 = findViewById(R.id.E1);
        wocom2= findViewById(R.id.E2);
        woman1card=findViewById(R.id.wc1);
        woman2card=findViewById(R.id.wc2);



        woman1card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wocom1.setEnabled(false);
                wocom2.setEnabled(true);
                woman2card.setCardBackgroundColor(Color.WHITE);
                woman1card.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedWomen = wocom1.getText().toString();
            }
        });

        woman2card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wocom2.setEnabled(false);
                wocom1.setEnabled(true);
                woman1card.setCardBackgroundColor(Color.WHITE);
                woman2card.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedWomen = wocom2.getText().toString();
            }
        });

        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        final String selectedSecetary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);
        final String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);
        final String selectedOrganizer = getIntent().getStringExtra(AppConstants.selectedOrganaString);

        Toast.makeText(this, String.format("{%s} {%s} {%s} {%s}", selectedPresident, selectedSecetary, selectedFinancial, selectedOrganizer), Toast.LENGTH_SHORT).show();

        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedWomen==null || selectedWomen.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                } else {

                    Intent women = new Intent(getApplicationContext(), SelectedVotes.class);
                    women.putExtra(AppConstants.selectedFinancialString, selectedFinancial);
                    women.putExtra(AppConstants.selectedSecretaryString, selectedSecetary);
                    women.putExtra(AppConstants.selectedPresidentString, selectedPresident);
                    women.putExtra(AppConstants.selectedWocomString, selectedWomen);
                    women.putExtra(AppConstants.selectedOrganaString, selectedOrganizer);

                    startActivity(women);
                }
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Women's Commissioner");
    }
}
