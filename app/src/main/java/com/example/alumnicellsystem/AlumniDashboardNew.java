package com.example.alumnicellsystem;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class AlumniDashboardNew extends AppCompatActivity {

    FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_events:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.alumni_dashboard_frame, new ViewEventsFrag());
                    ft.commit();
                    return true;
                case R.id.navigation_profile:
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.alumni_dashboard_frame, new AlumniProfileFrag());
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_dashboard_new);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.alumni_dashboard_frame, new ViewEventsFrag());
        ft.commit();
    }

}
