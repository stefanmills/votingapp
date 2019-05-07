package com.example.votingapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;


public class Settings extends AppCompatActivity {
    private CardView buttonsetpro;
    private CardView buttonsethelp;
    private CardView buttonsetacc;
    private CardView buttonsetshare;
    private PrefsManager prefsManager;
    private TextView usernameText;
    String Displayname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsManager = new PrefsManager(this);

        setContentView(R.layout.settings);
        buttonsetpro = findViewById(R.id.namebut);
        buttonsetshare = findViewById(R.id.invite);
        buttonsetacc = findViewById(R.id.accounts);
        buttonsethelp = findViewById(R.id.sethelp);
        usernameText= findViewById(R.id.displayname);

        Displayname= prefsManager.getUsername();
        usernameText.setText(Displayname);

        buttonsetpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account= new Intent(getApplication(),Profile.class);
                startActivity(account);

            }
        });

        buttonsethelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help= new Intent(getApplicationContext(),Help.class);
                startActivity(help);
            }
        });

        buttonsetshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share= new Intent(Intent.ACTION_SEND );
                share.setType("text/plain");
                String shareBodyText = "Check out this app. Help you vote in your comfort zone";
                share.putExtra(android.content.Intent.EXTRA_SUBJECT,"Share with friends");
                share.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(share);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));



    }
}
