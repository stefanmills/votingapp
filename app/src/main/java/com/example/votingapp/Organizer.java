package com.example.votingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Organizer extends AppInfo {

    private FloatingActionButton buttonOrganizer;
    private TextView org1text;
    private TextView org2text;
    private String selectedOrganizer;
    private CardView organa1;
    private CardView  organa2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.organizer);

        buttonOrganizer= findViewById(R.id.btOrganizer);
        org1text = findViewById(R.id.D1);
        org2text= findViewById(R.id.D2);
        organa1=findViewById(R.id.org1card);
        organa2=findViewById(R.id.org2card);




        organa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                org1text.setEnabled(false);
                org2text.setEnabled(true);
                organa2.setCardBackgroundColor(Color.WHITE);
                organa1.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedOrganizer = org1text.getText().toString();
            }
        });

        organa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                org2text.setEnabled(false);
                org1text.setEnabled(true);
                organa1.setCardBackgroundColor(Color.WHITE);
                organa2.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedOrganizer = org2text.getText().toString();
            }
        });


        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        final String selectedSecetary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);
        final String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);



        Toast.makeText(this, String.format("{%s} {%s} {%s}", selectedPresident, selectedSecetary, selectedFinancial), Toast.LENGTH_SHORT).show();

        buttonOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOrganizer==null || selectedOrganizer.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                } else {

                    Intent organa = new Intent(getApplicationContext(), WomensCommissioner.class);
                    organa.putExtra(AppConstants.selectedFinancialString, selectedFinancial);
                    organa.putExtra(AppConstants.selectedSecretaryString, selectedSecetary);
                    organa.putExtra(AppConstants.selectedPresidentString, selectedPresident);
                    organa.putExtra(AppConstants.selectedOrganaString, selectedOrganizer);

                    startActivity(organa);
                }
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Organizer");
    }
}
