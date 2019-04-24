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

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    String Username, Password;
    private TextInputEditText textInputUsername;
    private TextInputEditText textInputPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputUsername = findViewById(R.id.text_input_Username);
        textInputPassword = findViewById(R.id.text_input_Password);
        buttonLogin = findViewById(R.id.btLogin);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    public void login() {
        Username = textInputUsername.getText().toString(); //this enables you to get the text from the username box
        Password = textInputPassword.getText().toString();

        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(this, "Please input your reference number", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please input your password", Toast.LENGTH_LONG).show();
        } else {

            final String link = "http://10.0.2.2/SMSVoting/androidLoginApi.php";

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AndroidNetworking.post(link)
                            .addBodyParameter("username",Username)
                            .addBodyParameter("password",Password)
                            .setTag(TAG)
                            .setPriority(Priority.IMMEDIATE)
                            .build()
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(TAG, "Response is: " + response);
                                    Toast.makeText(getApplicationContext(), response.trim(), Toast.LENGTH_LONG).show();
                                    if(response.contains("logged")) {
                                        goToVote();
                                    }else {
                                        Log.d("wrong details", "wrong details");
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.e(TAG, "An Error occurred: " + anError.getErrorDetail());
                                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            });
        }

    }

    private void goToVote() {
        Intent votepage = new Intent(getApplicationContext(), VotePage.class);
        startActivity(votepage);
        finish();

    }
}
