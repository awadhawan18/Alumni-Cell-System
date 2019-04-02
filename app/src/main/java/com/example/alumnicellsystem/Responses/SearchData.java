package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchData implements Parcelable
{

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
    private String mobile;
    @SerializedName("alter_mobile")
    @Expose
    private String alterMobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("company_1")
    @Expose
    private String company1;
    @SerializedName("company_2")
    @Expose
    private String company2;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Long v;
    public final static Parcelable.Creator<SearchData> CREATOR = new Creator<SearchData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchData createFromParcel(Parcel in) {
            return new SearchData(in);
        }

        public SearchData[] newArray(int size) {
            return (new SearchData[size]);
        }

    }
            ;

    protected SearchData(Parcel in) {
        this.enrollmentNo = ((String) in.readValue((String.class.getClassLoader())));
        this.branch = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.alterMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.company1 = ((String) in.readValue((String.class.getClassLoader())));
        this.company2 = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public SearchData() {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlterMobile() {
        return alterMobile;
    }

    public void setAlterMobile(String alterMobile) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getV() {
        return v;
    }

    public void setV(Long v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("enrollmentNo"+ enrollmentNo).append("branch"+ branch).append("name"+ name).append("gender"+ gender).append("dob"+ dob).append("address"+ address).append("mobile"+ mobile).append("alterMobile"+ alterMobile).append("email"+ email).append("company1"+ company1).append("company2"+ company2).append("id"+ id).append("v"+ v).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
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
        dest.writeValue(id);
        dest.writeValue(v);
    }

    public int describeContents() {
        return 0;
    }

}