package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Selected Votes");

}
}
