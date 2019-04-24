package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.ResultReceiver;
import android.support.design.card.MaterialCardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Dashboard extends AppCompatActivity {

    private LinearLayout customSearch, uploadAlumni, profile, viewFaculty, addFaculty, addEvents, viewEvents;
    private MaterialCardView addFacultyCardView, uploadAlumniCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("Dashboard");

        customSearch = findViewById(R.id.custom_search);
        profile = findViewById(R.id.profile);
        uploadAlumni = findViewById(R.id.upload_alumni);
        viewFaculty = findViewById(R.id.view_faculties);
        addFaculty = findViewById(R.id.add_faculty);
        addEvents = findViewById(R.id.add_events);
        viewEvents = findViewById(R.id.view_events);

        addFacultyCardView = findViewById(R.id.add_faculty_cardview);
        uploadAlumniCardView = findViewById(R.id.upload_alumni_cardview);

        customSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomSearch.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("finisher", new ResultReceiver(null) {

                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        Dashboard.this.finish();
                    }
                });

                startActivity(intent);
            }
        });

        viewFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewFaculty.class));
            }
        });


        addEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddEvents.class));
            }
        });

        viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ViewEvents.class));
            }
        });

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddFaculty.class));
            }
        });

        uploadAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadActivity.class));
            }
        });

        SharedPreferences details = getSharedPreferences("userDetail", MODE_PRIVATE);
        Long role = details.getLong("Role",0);

        if (role==1){
            addFacultyCardView.setVisibility(View.INVISIBLE);
            uploadAlumniCardView.setVisibility(View.INVISIBLE);
        }



    }


}