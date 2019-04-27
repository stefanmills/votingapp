package com.example.votingapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final int PRIVATE_MODE = 0;
    private final String APP_NAME = "VOTE_APP";

    public PrefsManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(APP_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    private final String REFERENCE_NUMBER = "REFERENCE_NUMBER";
    private final String PASSWORD = "PASSWORD";
    private final String UNIQUE_ID = "UNIQUE_ID";
    private final String USERNAME = "USERNAME";








    public void setReferenceNumber(String referenceNumber) {
        editor.putString(REFERENCE_NUMBER, referenceNumber);
        editor.apply();
    }

    public String getReferenceNumber() {
        return preferences.getString(REFERENCE_NUMBER, null);
    }

    public void setPassword(String Password) {
        editor.putString(PASSWORD, Password);
        editor.apply();
    }

    public String getPassword() {
        return preferences.getString(PASSWORD, null);
    }
    public void setUniqueID(String UniqueID) {
        editor.putString(UNIQUE_ID, UniqueID);
        editor.apply();
    }

    public String getUniqueID() {
        return preferences.getString(UNIQUE_ID, null);
    }

    public void setUsername(String Username) {
        editor.putString(USERNAME, Username);
        editor.apply();
    }

    public String getUsername() {
        return preferences.getString(USERNAME, null);
    }



    public void clearStorage() {
        editor.clear().apply();
    }
}

