package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class President extends AppCompatActivity {
    private FloatingActionButton buttonPresident;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.president); // this shows you where you wonna direct it to
        buttonPresident= findViewById(R.id.btPresident);

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
