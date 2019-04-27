package com.example.votingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Profile extends AppCompatActivity {

    private PrefsManager prefsManager;
    private TextView profilenameText;
    String Displayname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsManager= new PrefsManager(this);





        setContentView(R.layout.profile);
        profilenameText= findViewById(R.id.profilename);

        Displayname= prefsManager.getUsername();
        profilenameText.setText(Displayname);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");

    }
}
