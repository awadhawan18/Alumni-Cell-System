package com.example.alumnicellsystem;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlumniDashboard extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_dashboard);

        fab = findViewById(R.id.alumniProfileFab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AlumniProfile.class);
                intent.putExtra("finisher", new ResultReceiver(null){

                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        AlumniDashboard.this.finish();
                    }
                });

                startActivity(intent);
            }
        });
    }
}
