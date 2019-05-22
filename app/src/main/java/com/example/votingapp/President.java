package com.example.votingapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.votingapp.Adapter.CandidateAdapter;
import com.example.votingapp.Model.CandidateDisplay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class President extends AppCompatActivity {

    public static String selectedPresident = "";
    public static String selectedPresidentID = "";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CandidateAdapter candidateAdapter;
    private FloatingActionButton buttonPresident;
    private ArrayList<CandidateDisplay> candidateDisplays = new ArrayList<>();
    private PrefsManager prefsManager;
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.president); // this shows you where you wonna direct it to

        prefsManager = new PrefsManager(this);

        buttonPresident = findViewById(R.id.btPresident);

        recyclerView = findViewById(R.id.presidentList);

        if (candidateDisplays.size() == 0) {
            getCandidates();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("President");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4682B4")));

    }


    public void initAdapters() {
        candidateAdapter = new CandidateAdapter(this, candidateDisplays);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(candidateAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
//        initListeners();
    }


    public void getCandidates() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.get("http://smsvotingpro.ga/androidPrezNameApi.php")
                        .setPriority(Priority.IMMEDIATE)
                        .setTag(this.getClass().getSimpleName())
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
//                                Log.d("test", response.toString());
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject candidate = response.getJSONObject(i);
                                        CandidateDisplay newCandidate = ResponseUtils.getCandidateFromJSONObject(candidate);
                                        candidateDisplays.add(newCandidate);
                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }

                                initAdapters();
                                initListeners();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d(President.class.getSimpleName(), "error: " + anError.getErrorBody());
                            }
                        });
            }
        });

    }

    public void setSelectedPresident(String name, String id) {
        Log.d("test", selectedPresident);
        selectedPresident = name;
        selectedPresidentID = id;
    }

    public void initListeners() {
        buttonPresident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("test10", selectedPresident);

                if (selectedPresident == null || selectedPresident.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                 //   Log.d("test100", selectedPresident);
//
                } else {
                    Intent secretary = new Intent(getApplicationContext(), SecretaryPage.class);
                    secretary.putExtra(AppConstants.selectedPresidentString, selectedPresident);
                    startActivity(secretary);
                }
            }
        });
    }

}
