package com.example.votingapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class SelectedVotes extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private Button buttonEdit;
    private Button buttonConfirm;
    private TextView president;
    private TextView secretary;
    private TextView financial;
    private TextView womens;
    private TextView organa;
    private PrefsManager prefsManager;
    private String referenceNumber, Password, UniqueID, prezVote, secVote, finSecVote, orgVote, woComVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedvotes); // this shows you where you wonna direct it to

        // for the button
        buttonConfirm = findViewById(R.id.btConfirm);
        buttonEdit = findViewById(R.id.btEdit);

        //for the textview
        president = findViewById(R.id.txtPresident);
        secretary = findViewById(R.id.txtSecretary);
        financial = findViewById(R.id.txtFinancial);
        organa = findViewById(R.id.txtOrgana);
        womens = findViewById(R.id.txtWomen);

        //for prefManager
        prefsManager = new PrefsManager(this);
        referenceNumber = prefsManager.getReferenceNumber();
        Password = prefsManager.getPassword();
        UniqueID = prefsManager.getUniqueID();
        prezVote = President.selectedPresidentID;
        secVote = SecretaryPage.selectedSecetaryID;
        finSecVote = Financial.selectedFinancialID;
        orgVote = Organizer.selectedOrganizerID;
        woComVote = WomensCommissioner.selectedWomenID;

//        JSONObject object = new JSONObject();
//        try {
//            object.put("prezVote", President.selectedPresidentID);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), President.class);
                startActivity(back);

            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(SelectedVotes.this);
                builder.setTitle("Submit");
                builder.setMessage("Are you sure you want to submit your vote?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        submit();
                    }
                }).setNegativeButton("No",null).show();

            }
        });

        String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);
        String selectedWocom = getIntent().getStringExtra(AppConstants.selectedWocomString);
        String selectedOrgana = getIntent().getStringExtra(AppConstants.selectedOrganaString);
        String selectedSecretary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);


        president.setText(String.format("President: %s", selectedPresident));
        secretary.setText(String.format("Secretary: %s", selectedSecretary));
        financial.setText(String.format("Financial Secretary: %s", selectedFinancial));
        organa.setText(String.format("Organizer: %s", selectedOrgana));
        womens.setText(String.format("Women's Comm.: %s", selectedWocom));


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Selected Votes");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4682B4")));


    }

    public void submit() {
        final String link = "http://smsvotingpro.ga/androidVote.php";

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Submitting votes");
        dialog.setCancelable(false);
        dialog.show();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.post(link)
                        .addBodyParameter("prezVote", prezVote)
                        .addBodyParameter("secVote", secVote)
                        .addBodyParameter("finSecVote", finSecVote)
                        .addBodyParameter("orgVote", orgVote)
                        .addBodyParameter("woComVote", woComVote)
                        .addBodyParameter("username",referenceNumber)
                        .addBodyParameter("password", Password)
                        .addBodyParameter("voterID",UniqueID)

                        .setTag(TAG)
                        .setPriority(Priority.IMMEDIATE)
                        .build()
                        .getAsString(new StringRequestListener() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Response is: " + response);
                                dialog.dismiss();
                                //Toast.makeText(getApplicationContext(), response.trim(), Toast.LENGTH_LONG).show();
                                if (response.contains("Congratulations")) {
                                    Toast.makeText(getApplicationContext(), "Vote Submitted", Toast.LENGTH_LONG).show();
                                    Intent vote = new Intent(getApplicationContext(), VotePage.class);
                                    startActivity(vote);
                                } else if (response.contains("Sorry")) {

                                    Toast.makeText(getApplicationContext(), "Sorry you have already cast your vote", Toast.LENGTH_LONG).show();
                                    Intent vote = new Intent(getApplicationContext(), VotePage.class);
                                    startActivity(vote);
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(),"Sorry unexpected error occured",Toast.LENGTH_LONG).show();
                            }


                        });
            }
        });
    }
}