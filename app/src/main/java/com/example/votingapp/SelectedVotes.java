package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedVotes extends AppCompatActivity {
    private Button buttonEdit;
    private Button buttonConfirm;
    private TextView president;
    private TextView secretary;
    private TextView financial;
    private TextView womens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedvotes); // this shows you where you wonna direct it to

        // for the button
        buttonConfirm= findViewById(R.id.btConfirm);
        buttonEdit= findViewById(R.id.btEdit);

        //for the textview
        president= findViewById(R.id.txtPresident);

        secretary=findViewById(R.id.txtSecretary);
        financial=findViewById(R.id.txtFinancial);
        womens= findViewById(R.id.txtWomen);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back= new Intent(getApplicationContext(), President.class);
                startActivity(back);

            }
        });




        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(),"Vote sent",Toast.LENGTH_LONG).show();
                // but before the next line it should send the votes to the xerver
                Intent vote= new Intent(getApplicationContext(), VotePage.class);
                startActivity(vote);
            }
        });

        String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);
        String selectedWocom = getIntent().getStringExtra(AppConstants.selectedWocomString);
        String selectedSecretary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);


        president.setText(String.format("President: %s", selectedPresident));
        secretary.setText(String.format("Secretary: %s",selectedSecretary));
        financial.setText(String.format("Financial Secretary: %s",selectedFinancial));;
        womens.setText(String.format("Women's Comm.: %s",selectedWocom));;

       // Toast.makeText(this, String.format("{%s} {%s} {%s} {%s}", selectedPresident, selectedSecretary, selectedFinancial, selectedWocom), Toast.LENGTH_LONG).show();



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Selected Votes");




}
}
