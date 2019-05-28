package com.example.votingapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private static final String RESULTS ="Results";
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
    private final String SELECTED_PRESIDENT = "selected_president";
    private final String FINGER_ID="FINGER_ID";
    private final String FINGER_SUCCESS = "FINGER_SUCCESS";






    public void setReferenceNumber(String referenceNumber) {
        editor.putString(REFERENCE_NUMBER, referenceNumber);
        editor.apply();
    }

    public String getReferenceNumber()
    {
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
    public void setFingerUniqueID(String UniqueID) {
        editor.putString(FINGER_ID, UniqueID);
        editor.apply();
    }

    public void setFingerSuccess(boolean fingerSuccess) {
        editor.putBoolean(FINGER_SUCCESS, fingerSuccess);
        editor.apply();
    }

    public void  setSelectedPresident(String name) {
        editor.putString(SELECTED_PRESIDENT, name).apply();
    }

    public String getSelectedSecetary() {
        return preferences.getString(SELECTED_PRESIDENT, null);
    }

    public String getUniqueID() {
        return preferences.getString(UNIQUE_ID, null);
    }

    public String getFingerUniqueID() {
        return preferences.getString(FINGER_ID, null);
    }

    public boolean getfingerSuccess() {
        return preferences.getBoolean(FINGER_SUCCESS, false);
    }

    public void setUsername(String Username) {
        editor.putString(USERNAME, Username);
        editor.apply();
    }

    public String getUsername() {
        return preferences.getString(USERNAME, null);
    }

    public void setResults(String Results) {
        editor.putString(RESULTS,Results);
        editor.apply();
    }

    public String getResults() {
        return preferences.getString(RESULTS, null);
    }



    public void clearStorage() {
        editor.clear().apply();
    }
}

