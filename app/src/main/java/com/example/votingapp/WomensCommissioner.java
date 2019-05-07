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
import android.widget.TextView;
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

public class WomensCommissioner extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CandidateAdapter candidateAdapter;
    private ArrayList<CandidateDisplay> candidateDisplays = new ArrayList<>();

    private FloatingActionButton buttonWomen;
    private TextView wocom1;
    private TextView wocom2;
    public static String selectedWomen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.womenscommissioner); // this shows you where you wonna direct it to
        buttonWomen= findViewById(R.id.btWomen);
        recyclerView = findViewById(R.id.wocomList);

        if (candidateDisplays.size() == 0) {
            candidateDisplays = getCandidates();
            initAdapters();

        }


        final String selectedPresident = getIntent().getStringExtra(AppConstants.selectedPresidentString);
        final String selectedSecetary = getIntent().getStringExtra(AppConstants.selectedSecretaryString);
        final String selectedFinancial = getIntent().getStringExtra(AppConstants.selectedFinancialString);
        final String selectedOrganizer = getIntent().getStringExtra(AppConstants.selectedOrganaString);

        Toast.makeText(this, String.format("{%s} {%s} {%s} {%s}", selectedPresident, selectedSecetary, selectedFinancial, selectedOrganizer), Toast.LENGTH_SHORT).show();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable( new ColorDrawable(Color.parseColor("#4682B4")));
        actionBar.setTitle("Women's Commissioner");
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
                AndroidNetworking.get("http://10.0.2.2/SMSVoting/androidWocomNameApi.php")
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
    public void setSelectedWomen(String name){
        selectedWomen = name;
        Log.d("test", selectedWomen);
    }
    public  void initListeners(){

        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedWomen==null || selectedWomen.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a candidate", Toast.LENGTH_LONG).show();
                } else {

                    Intent women = new Intent(getApplicationContext(), SelectedVotes.class);
                    women.putExtra(AppConstants.selectedFinancialString, Financial.selectedFinancial);
                    women.putExtra(AppConstants.selectedSecretaryString, SecretaryPage.selectedSecetary);
                    women.putExtra(AppConstants.selectedPresidentString, President.selectedPresident);
                    women.putExtra(AppConstants.selectedWocomString, WomensCommissioner.selectedWomen);
                    women.putExtra(AppConstants.selectedOrganaString, Organizer.selectedOrganizer);

                    startActivity(women);
                }
            }
        });

    }
}
