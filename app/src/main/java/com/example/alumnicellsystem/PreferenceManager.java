package com.example.alumnicellsystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alumnicellsystem.Constants.DesignationValues;
import com.example.alumnicellsystem.R;
import com.example.alumnicellsystem.Responses.LoginData;
import com.example.alumnicellsystem.Responses.LoginResponse;

public class PreferenceManager {

    private SharedPreferences sharedPreferences;
    private Context context;
    private LoginResponse loginResponse;

    public PreferenceManager(Context context, LoginResponse loginResponse) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userDetail", Context.MODE_PRIVATE);
        this.loginResponse = loginResponse;
    }

    public void writePref(){

        LoginData loginData = loginResponse.getData();
        String depart = loginData.getDepartment();
        Integer departmentCode = 0;
        switch (depart){

            case "CSE": departmentCode = 1;break;
            case "ECE": departmentCode = 2;break;
            case "IT": departmentCode = 3;break;
            case "EEE": departmentCode = 4;break;
            case "ICE": departmentCode = 5;
        }
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("Name", loginData.getName());
        editor.putString("Designation", loginData.getDesignation().toString());
        editor.putString("Department", (departmentCode.toString()));
        editor.putString("Email", loginData.getEmail());
        editor.putString("Contact", loginData.getPhoneNo());
        editor.commit();
    }
}
