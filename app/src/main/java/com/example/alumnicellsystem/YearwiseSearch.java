package com.example.alumnicellsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class YearwiseSearch extends AppCompatActivity {

    private Spinner yearSP;
    private Button submit;
    private String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearwise_search);

        yearSP = findViewById(R.id.year);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                year = yearSP.getSelectedItem().toString();
                //TODO: The Api call comes here

                startActivity(new Intent(getApplicationContext(), AlumniList.class));
            }
        });
    }
}
