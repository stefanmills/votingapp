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

public class SecretaryPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CandidateAdapter candidateAdapter;

    private FloatingActionButton buttonSecretary;

    public static String selectedSecetary;
ActionBar actionBar;
    private ArrayList<CandidateDisplay> candidateDisplays = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secretarypage); // this shows you where you wonna direct it to
        buttonSecretary = findViewById(R.id.btSecretary);
        recyclerView = findViewById(R.id.secretaryList);

        if (candidateDisplays.size() == 0) {
            candidateDisplays = getCandidates();
            initAdapters();

        }



        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);

        Toast.makeText(this, String.format("{%s}", selectedPresident), Toast.LENGTH_LONG).show();

   ActionBar actionBar = getSupportActionBar();
        setTitle("Secretary");
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));

    }

        public void initAdapters () {
            candidateAdapter = new CandidateAdapter(this, getCandidates());
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            recyclerView.setAdapter(candidateAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
            initListeners();
        }

        public ArrayList<CandidateDisplay> getCandidates () {
            final ArrayList<CandidateDisplay> candidates = new ArrayList<>();

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AndroidNetworking.get("http://10.0.2.2/SMSVoting/androidSeceNameApi.php")
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
                                            candidates.add(newCandidate);
                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.d(President.class.getSimpleName(), "error: " + anError.getErrorBody());
                                }
                            });
                }
            });

            return candidates;
        }


        public void setSelectedSecretary(String name){
            selectedSecetary = name;
            Log.d("test", selectedSecetary);
        }

        public void initListeners(){
                    buttonSecretary.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (selectedSecetary == null || selectedSecetary.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                            } else {
                                Intent financial = new Intent(getApplicationContext(), Financial.class);
                                financial.putExtra(AppConstants.selectedSecretaryString,SecretaryPage.selectedSecetary);
                                financial.putExtra(AppConstants.selectedPresidentString, President.selectedPresident);
                                startActivity(financial);
                            }

                        }
                    });
                }
            }
