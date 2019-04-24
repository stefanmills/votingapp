package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UniqueID extends AppCompatActivity {
    String UniqueID;
    private TextInputEditText textUnique;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueid);


        textUnique = findViewById(R.id.uniqueid);
        buttonSubmit = findViewById(R.id.btSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submit = new Intent(getApplicationContext(), President.class);
                startActivity(submit);
            }
        });
}
}
