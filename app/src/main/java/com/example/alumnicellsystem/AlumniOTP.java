package com.example.alumnicellsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlumniOTP extends AppCompatActivity {

    private Button verfiy;
    private EditText otpET;
    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_otp);

        otpET = findViewById(R.id.otp);
        verfiy = findViewById(R.id.verify);

        verfiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: OTP verification code comes here. Also, Create a landing activity from login screen.

            }
        });
    }
}
