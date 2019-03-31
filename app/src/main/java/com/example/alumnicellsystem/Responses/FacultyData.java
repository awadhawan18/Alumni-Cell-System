package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyData implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("designation")
    @Expose
    private Long designation;
    @SerializedName("email")
    @Expose
    private String email;
    public final static Parcelable.Creator<FacultyData> CREATOR = new Creator<FacultyData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FacultyData createFromParcel(Parcel in) {
            return new FacultyData(in);
        }

        public FacultyData[] newArray(int size) {
            return (new FacultyData[size]);
        }

    }
            ;

    protected FacultyData(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.designation = ((Long) in.readValue((Long.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
    }

    public FacultyData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getDesignation() {
        return designation;
    }

    public void setDesignation(Long designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("name "+ name).append("department "+ department).append("designation "+ designation).append("email "+ email).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(department);
        dest.writeValue(designation);
        dest.writeValue(email);
    }

    public int describeContents() {
        return 0;
    }

}