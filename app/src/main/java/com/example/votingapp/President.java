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

public class President extends AppCompatActivity {
    private FloatingActionButton buttonPresident;
    private TextView pres1;
    private TextView pres2;
    private String selectedPresident;
    private CardView  president1;
    private CardView  president2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.president); // this shows you where you wonna direct it to
        buttonPresident= findViewById(R.id.btPresident);

      pres1 = findViewById(R.id.A1);
      pres2= findViewById(R.id.A2);
      president1=findViewById(R.id.candidate1);
      president2=findViewById(R.id.candidate2);


      president1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              pres1.setEnabled(false);
              pres2.setEnabled(true);
              president2.setCardBackgroundColor(Color.WHITE);
              president1.setCardBackgroundColor(Color.rgb(255, 253, 208));
              selectedPresident = pres1.getText().toString();
          }
      });

        president2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pres2.setEnabled(false);
                pres1.setEnabled(true);
                president1.setCardBackgroundColor(Color.WHITE);
                president2.setCardBackgroundColor(Color.rgb(255, 253, 208));
                selectedPresident = pres2.getText().toString();
            }
        });


        /**
         * once you save the data into the shared preferences, you read the value when you load the next page
         * if you go back to the previous page, check if the selected value stored in the preferences is null or not
         * if it is not null, then you display it.
         *
         * if(!(prefs.getSelectedPresident() == null)) {
         *     pres1.setEnabled(false);
         *     pres2.setEnabled(true);
         *
         *     selectedPresidentText.setText(prefs.getSelectedPresident());
         * }
         */



        buttonPresident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPresident == null || selectedPresident.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                }else {
                    Intent secretary= new Intent(getApplicationContext(), SecretaryPage.class);
                    secretary.putExtra(AppConstants.selectedPresidentString, selectedPresident);
//                secretary.getStringExtra()
                    startActivity(secretary);
                }
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("President");



    }
}
