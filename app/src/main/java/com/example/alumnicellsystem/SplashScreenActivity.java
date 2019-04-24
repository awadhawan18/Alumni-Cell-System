package com.example.alumnicellsystem;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.alumnicellsystem.Constants.UserFields;

import java.util.HashSet;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    //public static final String PREF_COOKIES = "PREF_COOKIES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //HashSet<String> cookies = (HashSet<String>) MyPreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getStringSet(PREF_COOKIES, new HashSet<String>());

                String user = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("User_Value", "0");

                Log.v("User value", user);

                if(user!=null && user.equalsIgnoreCase("1")){
                    Intent dashboard = new Intent(SplashScreenActivity.this, Dashboard.class);
                    startActivity(dashboard);
                }else if(user!=null && user.equalsIgnoreCase("2")){
                    Intent dashboard = new Intent(SplashScreenActivity.this, AlumniDashboardNew.class);
                    startActivity(dashboard);
                }
                else {
                    Intent homeIntent = new Intent(SplashScreenActivity.this, ChoiceActivity.class);
                    startActivity(homeIntent);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
