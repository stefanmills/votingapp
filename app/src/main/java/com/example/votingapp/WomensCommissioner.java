package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WomensCommissioner extends AppCompatActivity {
    private Button buttonWomen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womenscommissioner); // this shows you where you wonna direct it to
        buttonWomen= findViewById(R.id.btWomen);

        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent women= new Intent(getApplicationContext(), SelectedVotes.class);
                startActivity(women);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Women's Commissioner");
    }
}
