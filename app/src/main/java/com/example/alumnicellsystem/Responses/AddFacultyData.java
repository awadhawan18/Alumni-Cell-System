package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFacultyData implements Parcelable
{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("active")
    @Expose
    private String active;
    public final static Parcelable.Creator<AddFacultyData> CREATOR = new Creator<AddFacultyData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddFacultyData createFromParcel(Parcel in) {
            return new AddFacultyData(in);
        }

        public AddFacultyData[] newArray(int size) {
            return (new AddFacultyData[size]);
        }

    }
            ;

    protected AddFacultyData(Parcel in) {
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AddFacultyData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("email"+ email).append("role"+ role).append("active"+ active).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(email);
        dest.writeValue(role);
        dest.writeValue(active);
    }

    public int describeContents() {
        return 0;
    }

}