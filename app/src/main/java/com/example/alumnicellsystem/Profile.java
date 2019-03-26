package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Profile extends AppCompatActivity {

    private EditText nameET, emailET, contactET;
    private Spinner designationSP, departmentSP;
    private Button update, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        contactET = findViewById(R.id.contactET);
        designationSP = findViewById(R.id.designation);
        departmentSP = findViewById(R.id.department);
        update = findViewById(R.id.updateBtn);
        logout = findViewById(R.id.logoutBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Profile updation code comes here
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                memes.remove("PREF_COOKIES");
                memes.commit();

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
