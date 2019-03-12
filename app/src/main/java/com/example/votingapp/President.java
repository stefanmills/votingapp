package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class President extends AppCompatActivity {
    private FloatingActionButton buttonPresident;
    private Button pres1;
    private Button pres2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.president); // this shows you where you wonna direct it to
        buttonPresident= findViewById(R.id.btPresident);

      pres1 = findViewById(R.id.candidate1);
      pres2= findViewById(R.id.candidate2);


      pres1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              pres1.setEnabled(false);
              pres2.setEnabled(true);
          }
      });

        pres2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pres2.setEnabled(false);
                pres1.setEnabled(true);
            }
        });




        buttonPresident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secretary= new Intent(getApplicationContext(), SecretaryPage.class);
                startActivity(secretary);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("President");



}
}
