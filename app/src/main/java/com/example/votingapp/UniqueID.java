package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private PrefsManager prefsManager;
    private String referenceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueid);

        prefsManager = new PrefsManager(this);

        referenceNumber = prefsManager.getReferenceNumber();

        textUnique = findViewById(R.id.uniqueid);
        buttonSubmit = findViewById(R.id.btSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unique();
            }


        });
    }

    public void unique() {
        UniqueID = textUnique.getText().toString(); //this enables you to get the text from the username box


        if (TextUtils.isEmpty(UniqueID)) {
            Toast.makeText(this, "Please input your unique number", Toast.LENGTH_LONG).show();

        } else {
            final String link = "http://10.0.2.2/SMSVoting/androidUniqueIDApi.php";

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