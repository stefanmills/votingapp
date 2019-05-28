package com.example.votingapp;

import android.Manifest;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class UniqueID extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private String UniqueID;
    private TextInputEditText textUnique;
    private Button buttonSubmit;
    private TextView mParaLabel;
    private TextView paralabel;
    private PrefsManager prefsManager;
    private ImageView fingerp;
    private String referenceNumber;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueid);


        prefsManager = new PrefsManager(this);

        referenceNumber = prefsManager.getReferenceNumber();

        textUnique = findViewById(R.id.uniqueid);
        buttonSubmit = findViewById(R.id.btSubmit);
        fingerp=findViewById(R.id.fingerimage);
        paralabel=findViewById(R.id.paraLabel);
        mParaLabel=findViewById(R.id.paraLabel);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                President.selectedPresident = "";
                SecretaryPage.selectedSecetary = "";
                Financial.selectedFinancial = "";
                Organizer.selectedOrganizer = "";
                WomensCommissioner.selectedWomen = "";
                unique();
            }


        });

        // TO DO: ANDRODI VERSION SHOULD BE GREATER OR EQUAL TO MASH
        // WHETHER DEVICE HAS A FINGERPRINT SCANNER.
        // CHECK WHETHER THE USER OF THE PHONE IS USING THE FINGERPRINT FEATURE.
        // LOCK SCREEN IS SECURE WUTH ONE TYPE OF LOCK
        // AT LEAST ONE FINGERPRINT IS REGISTERED

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            fingerprintManager=(FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager= (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            if (!fingerprintManager.isHardwareDetected()){

                mParaLabel.setText("Fingerprint Scanner not detected on device. Use the Unique ID sent");


            }
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED){

                mParaLabel.setText("Permission not granted to use fingerprint scanner");
            }
            else if (!keyguardManager.isKeyguardSecure()){
                mParaLabel.setText("Add lock to your phone in settings");
            }
            else if (!fingerprintManager.hasEnrolledFingerprints()){
mParaLabel.setText("You should add at least one fingerprint to the device to use this feature.");
            }
            else {
                mParaLabel.setText("Place your thumb to access the vote menu.");

                FingerprintHandler fingerprintHandler= new FingerprintHandler(this);
                fingerprintHandler.startAuth(fingerprintManager, null);


            }
        }

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Unique ID");
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));
    }

    public void unique() {
        UniqueID = textUnique.getText().toString(); //this enables you to get the text from the username box


        if (TextUtils.isEmpty(UniqueID)) {
            Toast.makeText(this, "Please input your unique number", Toast.LENGTH_LONG).show();

        } else {
            final String link = "http://smsvotingpro.ga/androidUniqueIDApi.php";
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Please wait");
            dialog.setCancelable(false);
            dialog.show();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AndroidNetworking.post(link)
                            .addBodyParameter("username", referenceNumber)
                            .addBodyParameter("uniqueid", UniqueID)
                            .setTag(TAG)
                            .setPriority(Priority.IMMEDIATE)
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(TAG, "Response is: " + response);
                                    dialog.dismiss();
                                    //Toast.makeText(getApplicationContext(), response.trim(), Toast.LENGTH_LONG).show();
                                    if (response.contains("correct")) {
                                        Intent submit = new Intent(getApplicationContext(), President.class);
                                        startActivity(submit);

                                        prefsManager.setUniqueID(UniqueID);

                                    }
                                    else {
                                        Log.d("wrong details", "wrong details");
                                        Toast.makeText(getApplicationContext(), "Wrong Unique ID", Toast.LENGTH_LONG).show();

                                    }

                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.e(TAG, "An Error occurred: " + anError.getErrorDetail());
                                    Toast.makeText(getApplicationContext(), "Incorrect ID", Toast.LENGTH_LONG).show();
                                }
                            });
                }

            });
        }
    }
}