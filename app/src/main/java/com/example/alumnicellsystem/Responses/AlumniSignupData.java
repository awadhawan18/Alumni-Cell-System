package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlumniSignupData implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("enrollment_no")
    @Expose
    private String enrollmentNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    public final static Parcelable.Creator<AlumniSignupData> CREATOR = new Creator<AlumniSignupData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AlumniSignupData createFromParcel(Parcel in) {
            return new AlumniSignupData(in);
        }

        public AlumniSignupData[] newArray(int size) {
            return (new AlumniSignupData[size]);
        }

    }
            ;

    protected AlumniSignupData(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.enrollmentNo = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AlumniSignupData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("name"+ name).append("enrollmentNo"+ enrollmentNo).append("email"+ email).append("mobile"+ mobile).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(enrollmentNo);
        dest.writeValue(email);
        dest.writeValue(mobile);
    }

    public int describeContents() {
        return 0;
    }

}