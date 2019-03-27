package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchItem implements Parcelable
{

    @SerializedName("S. No.")
    @Expose
    private Long sNo;
    @SerializedName("enrollment_no")
    @Expose
    private String enrollmentNo;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobile")
    @Expose
    private Long mobile;
    @SerializedName("alter_mobile")
    @Expose
    private Long alterMobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("company_1")
    @Expose
    private String company1;
    @SerializedName("company_2")
    @Expose
    private String company2;
    public final static Parcelable.Creator<SearchItem> CREATOR = new Creator<SearchItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchItem createFromParcel(Parcel in) {
            return new SearchItem(in);
        }

        public SearchItem[] newArray(int size) {
            return (new SearchItem[size]);
        }

    }
            ;

    protected SearchItem(Parcel in) {
        this.sNo = ((Long) in.readValue((Long.class.getClassLoader())));
        this.enrollmentNo = ((String) in.readValue((String.class.getClassLoader())));
        this.branch = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((Long) in.readValue((Long.class.getClassLoader())));
        this.alterMobile = ((Long) in.readValue((Long.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.company1 = ((String) in.readValue((String.class.getClassLoader())));
        this.company2 = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SearchItem() {
    }

    public Long getSNo() {
        return sNo;
    }

    public void setSNo(Long sNo) {
        this.sNo = sNo;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getAlterMobile() {
        return alterMobile;
    }

    public void setAlterMobile(Long alterMobile) {
        this.alterMobile = alterMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany1() {
        return company1;
    }

    public void setCompany1(String company1) {
        this.company1 = company1;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("sNo"+ sNo).append("enrollmentNo"+ enrollmentNo).append("branch"+ branch).append("name"+ name).append("gender"+ gender).append("dob"+ dob).append("address"+ address).append("mobile"+ mobile).append("alterMobile"+ alterMobile).append("email"+ email).append("company1"+ company1).append("company2"+ company2).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sNo);
        dest.writeValue(enrollmentNo);
        dest.writeValue(branch);
        dest.writeValue(name);
        dest.writeValue(gender);
        dest.writeValue(dob);
        dest.writeValue(address);
        dest.writeValue(mobile);
        dest.writeValue(alterMobile);
        dest.writeValue(email);
        dest.writeValue(company1);
        dest.writeValue(company2);
    }

    public int describeContents() {
        return 0;
    }

}