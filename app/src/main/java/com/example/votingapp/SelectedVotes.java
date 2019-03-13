package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectedVotes extends AppCompatActivity {
    private Button buttonEdit;
    private Button buttonConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedvotes); // this shows you where you wonna direct it to
        buttonConfirm= findViewById(R.id.btConfirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // but before the next line it should send the votes to the xerver
                Intent vote= new Intent(getApplicationContext(), VotePage.class);
                startActivity(vote);
            }
        });

        String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);
        String selectedWocom = getIntent().getStringExtra(AppConstants.selectedWocomString);
        String selectedSecretary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);


        Toast.makeText(this, String.format("{%s} {%s} {%s} {%s}", selectedFinancial, selectedPresident, selectedSecretary, selectedWocom), Toast.LENGTH_LONG).show();



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Selected Votes");

}
}
