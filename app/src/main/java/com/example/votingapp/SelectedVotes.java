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
    private TextView organa;
    private PrefsManager prefsManager;
    private String referenceNumber,Password,UniqueID;
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
        organa=findViewById(R.id.txtOrgana);
        womens= findViewById(R.id.txtWomen);

        //for prefManager
        prefsManager=new PrefsManager(this);
        referenceNumber = prefsManager.getReferenceNumber();
        Password=prefsManager.getPassword();
        UniqueID= prefsManager.getUniqueID();


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
        String selectedOrgana = getIntent().getStringExtra(AppConstants.selectedOrganaString);
        String selectedSecretary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);


        president.setText(String.format("President: %s", selectedPresident));
        secretary.setText(String.format("Secretary: %s",selectedSecretary));
        financial.setText(String.format("Financial Secretary: %s",selectedFinancial));
        organa.setText(String.format("Organizer: %s",selectedOrgana));
        womens.setText(String.format("Women's Comm.: %s",selectedWocom));




        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Selected Votes");




}
}
