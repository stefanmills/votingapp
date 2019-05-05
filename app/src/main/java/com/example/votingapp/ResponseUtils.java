package com.example.votingapp;

import android.util.Log;

import com.example.votingapp.Model.CandidateDisplay;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseUtils {

    public static CandidateDisplay getCandidateFromJSONObject(JSONObject object) throws JSONException {
        Log.d("test2", object.toString());

        Log.d("test3", object.getString("FirstName"));

        CandidateDisplay candidateDisplay = new CandidateDisplay();
        candidateDisplay.setCandidateName(object.getString("FirstName")
                + " "+ object.getString("LastName"));
        candidateDisplay.setID(object.getString("BallotDetail"));
        candidateDisplay.setPosition(object.getString("PositionAspiring"));
        candidateDisplay.setPictureUrl(object.getString("ImageName"));

        return candidateDisplay;
    }
}
