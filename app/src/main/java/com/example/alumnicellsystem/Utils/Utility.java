package com.example.alumnicellsystem.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.alumnicellsystem.Constants.DesignationValues;
import com.example.alumnicellsystem.R;

public class Utility {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isStatusOk(Long status){
        return status == 200;
    }

    public static boolean isValidPhoneNo(CharSequence mobileNo) {
        return !TextUtils.isEmpty(mobileNo) &&
                mobileNo.length()==10;
    }

    public static boolean isOtpStatusOk(Long status){
        return status == 201;
    }

    public static int getDesignationValue(String designation){

        DesignationValues.Designations designationObject = DesignationValues.Designations.valueOf(designation.trim().toUpperCase());
        int designationValue;

        switch(designationObject){
            case PRINCIPAL: designationValue = 1; break;
            case ASSOCIATE_PROFESSOR: designationValue = 2; break;
            case ASSISTANT_PROFESSOR: designationValue = 3; break;
            case PROFESSOR: designationValue = 4; break;
            case OTHER: designationValue = 5; break;
            default: designationValue = 5; break;
        }
        return designationValue;
    }

    public static String getDesignationString(Context mContext, int number){
        String[] designations = mContext.getResources().getStringArray(R.array.designation);
        return designations[number];
    }

    public static String getEventTime(String date, String startTime, String endTime){
        return date.concat(", ").concat(startTime).concat("-").concat(endTime);
    }
}
