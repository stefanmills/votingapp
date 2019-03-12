package com.example.votingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class VotePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button buttonVote;
    private Button buttonResults;
    private DrawerLayout drawer;
    private NavigationView drawerNavigation;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case  R.id.Profile:
              Intent profile= new Intent(getApplicationContext(), Profile.class);
              startActivity(profile);
              return true;



            case  R.id.Settings:
                Intent set= new Intent(VotePage.this, Settings.class);
                startActivity(set);
                return true;


                case  R.id.Share:
                    Intent share= new Intent(VotePage.this, Share.class);
                    startActivity(share);
                    return true;
            case  R.id.Logout:
                drawer.closeDrawer(Gravity.START, false);

                AlertDialog.Builder builder= new AlertDialog.Builder(VotePage.this);
                builder.setTitle("Logout");
                builder.setMessage("Logout this app?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent login= new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(login);
finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }); AlertDialog alertDialog= builder.create();
                alertDialog.show();








//                Intent lout= new Intent(VotePage.this, Logout.class);
//                startActivity(lout);

                return true;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.votepage);

        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        buttonVote = (Button) findViewById(R.id.btVote);
        buttonResults = (Button) findViewById(R.id.btResults);
        drawer = findViewById(R.id.Drawer);
        drawerNavigation = findViewById(R.id.nav_view);


        NavigationView navigationView = findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.draweropen, R.string.drawerclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent president = new Intent(getApplicationContext(), President.class);
                startActivity(president);
            }
        });
        buttonResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewresultpage = new Intent(getApplicationContext(), ViewResultPage.class);
                startActivity(viewresultpage);
            }
        });



       /* @Override
        public void onBackPressed() {
if(drawer.isDrawerOpen(GravityCompat.START)){

    drawer.closeDrawer(GravityCompat.START);
} else {
            super.onBackPressed();
        } }*/


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");



}
}
