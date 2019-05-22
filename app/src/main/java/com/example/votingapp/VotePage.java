package com.example.votingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

public class VotePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button buttonVote;
    private Button buttonResults;
    private DrawerLayout drawer;
    private NavigationView drawerNavigation;
    private PrefsManager prefsManager;
    private View headerView;
    private TextView usernameText,votedisplayname;
    private final String TAG = this.getClass().getSimpleName();
    String  referenceNumber, Password;
    private CardView welcome;
    private  CardView ResultsCard;
    private String Displayname;
    ActionBar actionBar;
    DayNightSwitch dayNightSwitch;
    View backgroud_view;
    ImageView imageView;


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
                    Intent share= new Intent(Intent.ACTION_SEND );
                    share.setType("text/plain");
                    String shareBodyText = "Check out this app. Help you vote in your comfort zone. You can download or  app" +
                            "on google playstore  or visit our website via http://smsvotingpro.ga/";
                    share.putExtra(android.content.Intent.EXTRA_SUBJECT,"Share with friends");
                    share.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);

                    startActivity(share);
                    return true;

            case  R.id.Help:
                Intent help= new Intent(getApplicationContext(), Help.class);
                startActivity(help);
                return true;

            case  R.id.Logout:
                drawer.closeDrawer(Gravity.START, false);

                AlertDialog.Builder builder= new AlertDialog.Builder(VotePage.this);
                builder.setTitle("Logout");
                builder.setMessage("Logout this app?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        prefsManager.clearStorage();
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
        welcome=findViewById(R.id.welcomecard);
        dayNightSwitch= findViewById(R.id.daynight);
        backgroud_view=findViewById(R.id.backview);
        imageView=findViewById(R.id.propic);

        dayNightSwitch.setDuration(450);
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean isNight) {
              if (isNight){
                  backgroud_view.setAlpha(1f);
              }
              else{
                  backgroud_view.setAlpha(0f);
              }


            }
        });

        prefsManager = new PrefsManager(this);
        votedisplayname= findViewById(R.id.Votedisplay);

        Displayname= prefsManager.getUsername();
        votedisplayname.setText(Displayname);


        referenceNumber = prefsManager.getReferenceNumber();
        Password=prefsManager.getPassword();
        Log.d(TAG, " reference is : " + referenceNumber);



        final String link = "http://smsvotingpro.ga/androidDisplayName.php";


        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        buttonVote =  findViewById(R.id.btVote);
        buttonResults =findViewById(R.id.btResults);
        drawer = findViewById(R.id.Drawer);
        drawerNavigation = findViewById(R.id.nav_view);


        NavigationView navigationView = findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(this);


         headerView = navigationView.getHeaderView(0);
         usernameText = headerView.findViewById(R.id.Username);



//         usernameText.setText();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.draweropen, R.string.drawerclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unique = new Intent(getApplicationContext(), UniqueID.class);
                startActivity(unique);
            }
        });
        buttonResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewresultpage = new Intent(getApplicationContext(), ViewResultPage.class);
                startActivity(viewresultpage);
            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.post(link)
                        .addBodyParameter("username", referenceNumber)
                        .addBodyParameter("password", Password)
                        .setTag(TAG)
                        .setPriority(Priority.IMMEDIATE)
                        .build()
                        .getAsString(new StringRequestListener() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Response is: " + response);
                                usernameText.setText(response);

                                prefsManager.setUsername(response);
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });




        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));
        actionBar.setTitle("Home");



}
}
