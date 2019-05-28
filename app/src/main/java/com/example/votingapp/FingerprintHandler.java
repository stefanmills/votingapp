package com.example.votingapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;
    private PrefsManager prefsManager;


    public FingerprintHandler(Context context){
        this.context= context;
        prefsManager = new PrefsManager(this.context);


    }

    public  void startAuth( FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal= new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject, cancellationSignal,0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update ("There was an authentication error" + errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Authentication Failed", false);
        // input iuimgae here


    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        // this is for when you havent scanned your finger on scanner well

        this.update("Error" +helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("You can proceed to vote", true);
        prefsManager.setFingerSuccess(true);
        Intent submit = new Intent(this.context, President.class);

        this.context.startActivity(submit);




    }

    private void update(String s, boolean b) {
        TextView paralabel= ((Activity)context).findViewById(R.id.paraLabel);
        ImageView imageView= ((Activity)context).findViewById(R.id.fingerimage);

        paralabel.setText(s);

        if (b==false){
            paralabel.setTextColor(ContextCompat.getColor(context,R.color.red));
            imageView.setImageResource(R.drawable.ic_close_black_24dp);

        }
        else {
            paralabel.setTextColor(ContextCompat.getColor(context,R.color.black));
            imageView.setImageResource(R.drawable.ic_done_black_24dp);


        }
    }
}
