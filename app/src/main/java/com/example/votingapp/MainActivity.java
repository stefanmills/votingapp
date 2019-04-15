package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
            Intent votepage = new Intent(getApplicationContext(), VotePage.class);
            startActivity(votepage);
            finish();
        }

    }
}
