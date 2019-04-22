package com.example.alumnicellsystem;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Dashboard extends AppCompatActivity {

    private LinearLayout customSearch, uploadAlumni, profile, viewFaculty, addFaculty, addAlumni;

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
        addAlumni = findViewById(R.id.add_alumni);

        customSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomSearch.class));
            }
        });

        uploadAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadActivity.class));
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

        viewFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewFaculty.class));
            }
        });


        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddFaculty.class));
            }
        });

        addAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddEvents.class));
            }
        });

    }


}