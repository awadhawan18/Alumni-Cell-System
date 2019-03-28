package com.example.alumnicellsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private LinearLayout customSearch, yearWiseSearch, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("Dashboard");

        customSearch = findViewById(R.id.custom_search);
        yearWiseSearch = findViewById(R.id.view_alumni);
        profile = findViewById(R.id.profile);

        customSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomSearch.class));
            }
        });

        yearWiseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), YearwiseSearch.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("finisher", new ResultReceiver(null){

                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        Dashboard.this.finish();
                    }
                });

                startActivity(intent);
            }
        });

    }


}