package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecretaryPage extends AppCompatActivity {
    private Button buttonSecretary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secretarypage); // this shows you where you wonna direct it to
        buttonSecretary= findViewById(R.id.btSecretary);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Secretary");

        buttonSecretary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent financial= new Intent(getApplicationContext(), Financial.class);
                startActivity(financial);
            }
        });
}
}
