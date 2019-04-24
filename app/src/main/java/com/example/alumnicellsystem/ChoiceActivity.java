package com.example.alumnicellsystem;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    Button faculty, alumni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        faculty = findViewById(R.id.facultyLoginBtn);
        alumni = findViewById(R.id.alumniLoginBtn);

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("choiceFinisher", new ResultReceiver(null){

                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        ChoiceActivity.this.finish();
                    }
                });

                startActivity(intent);
            }
        });

        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AlumniLogin.class);
                intent.putExtra("choiceFinisher", new ResultReceiver(null){

                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        ChoiceActivity.this.finish();
                    }
                });

                startActivity(intent);
            }
        });
    }
}
