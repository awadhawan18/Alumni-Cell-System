package com.example.alumnicellsystem;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alumnicellsystem.Constants.DesignationValues;
import com.example.alumnicellsystem.R;
import com.example.alumnicellsystem.Responses.AlumniLoginData;
import com.example.alumnicellsystem.Responses.AlumniLoginResponse;
import com.example.alumnicellsystem.Responses.AlumniUpdateData;
import com.example.alumnicellsystem.Responses.LoginData;
import com.example.alumnicellsystem.Responses.LoginResponse;

public class MyPreferenceManager {

    private SharedPreferences sharedPreferences;
    private Context context;
    private LoginResponse loginResponse;
    private AlumniLoginResponse alumniLoginResponse;

    public MyPreferenceManager(Context context) {
        this.context = context;
    }

    public MyPreferenceManager(Context context, LoginResponse loginResponse) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userDetail", Context.MODE_PRIVATE);
        this.loginResponse = loginResponse;
    }

    public MyPreferenceManager(Context context, AlumniLoginResponse loginResponse){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("alumniDetails", Context.MODE_PRIVATE);
        this.alumniLoginResponse = loginResponse;
    }

    public void writePref(){

        LoginData loginData = loginResponse.getData();
        String depart = loginData.getDepartment();
        int departmentCode = 0;
        switch (depart){

            case "CSE": departmentCode = 1;break;
            case "ECE": departmentCode = 2;break;
            case "IT": departmentCode = 3;break;
            case "EEE": departmentCode = 4;break;
            case "ICE": departmentCode = 5;break;
            default: departmentCode = 1;
        }
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("User", "1");
        editor.putString("Name", loginData.getName());
        editor.putString("Designation", loginData.getDesignation().toString());
        editor.putString("Department", (String.valueOf(departmentCode)));
        editor.putString("Email", loginData.getEmail());
        editor.putString("Contact", loginData.getPhoneNo());
        editor.apply();
    }

    public void writeAlumniPref(){
        AlumniLoginData loginData = alumniLoginResponse.getData();

        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("User", "2");
        editor.putString("id", loginData.getId());
        editor.putString("Name", loginData.getName());
        editor.putString("Enrollment", loginData.getEnrollmentNo());
        editor.putString("Department", loginData.getBranch());
        editor.putString("Email", loginData.getEmail());
        editor.putString("Contact", loginData.getMobile());
        editor.putString("Address", loginData.getAddress());
        editor.putString("Company", loginData.getCompany1());
        editor.apply();
    }

    public void updateAlumniPref(AlumniUpdateData alumniUpdateData){
        SharedPreferences.Editor editor = context.getSharedPreferences("alumniDetails", Context.MODE_PRIVATE).edit();
        editor.putString("User", "2");
        editor.putString("id", alumniUpdateData.getId());
        editor.putString("Name", alumniUpdateData.getName());
        editor.putString("Enrollment", alumniUpdateData.getEnrollmentNo());
        editor.putString("Department", alumniUpdateData.getBranch());
        editor.putString("Email", alumniUpdateData.getEmail());
        editor.putString("Contact", alumniUpdateData.getMobile());
        editor.putString("Address", alumniUpdateData.getAddress());
        editor.putString("Company", alumniUpdateData.getCompany1());
        editor.apply();
    }
}
