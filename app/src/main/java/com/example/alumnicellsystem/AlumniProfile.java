package com.example.alumnicellsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.ResultReceiver;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlumniProfile extends AppCompatActivity {

    private EditText nameET, enrollmentET, branchET, companyET, emailET, contactEt, addressET;
    private Button update, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_profile);

        nameET = findViewById(R.id.nameET);
        enrollmentET = findViewById(R.id.roll_noET);
        branchET = findViewById(R.id.branchET);
        companyET = findViewById(R.id.companyET);
        emailET = findViewById(R.id.emailET);
        contactEt = findViewById(R.id.contactET);
        addressET = findViewById(R.id.addressET);

        update = findViewById(R.id.updateBtn);
        logout = findViewById(R.id.logoutBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Updation code comes here.
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                memes.remove("User_Value");
                memes.remove("userDetail");
                memes.commit();

                startActivity(new Intent(getApplicationContext(), ChoiceActivity.class));
                AlumniProfile.this.finish();
                ((ResultReceiver)getIntent().getParcelableExtra("finisher")).send(1, new Bundle());
            }
        });

        SharedPreferences details = getSharedPreferences("alumniDetails", MODE_PRIVATE);
        nameET.setText(details.getString("Name", null));
        enrollmentET.setText(details.getString("Enrollment", null));
        branchET.setText(details.getString("Department", null));
        companyET.setText(details.getString("Company", null));
        emailET.setText(details.getString("Email", null));
        contactEt.setText(details.getString("Contact", null));
        addressET.setText(details.getString("Address", null));
    }
}
