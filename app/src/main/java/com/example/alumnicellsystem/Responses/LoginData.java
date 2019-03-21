package com.example.alumnicellsystem.Responses;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData implements Parcelable {

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private Long designation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("role")
    @Expose
    private Long role;
    @SerializedName("otp")
    @Expose
    private Long otp;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("active")
    @Expose
    private Long active;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    public final static Parcelable.Creator<LoginData> CREATOR = new Creator<LoginData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginData createFromParcel(Parcel in) {
            return new LoginData(in);
        }

        public LoginData[] newArray(int size) {
            return (new LoginData[size]);
        }

    };

    protected LoginData(Parcel in) {
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.designation = ((Long) in.readValue((Long.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNo = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((Long) in.readValue((Long.class.getClassLoader())));
        this.otp = ((Long) in.readValue((Long.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((Long) in.readValue((Long.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginData() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDesignation() {
        return designation;
    }

    public void setDesignation(Long designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getOtp() {
        return otp;
    }

    public void setOtp(Long otp) {
        this.otp = otp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginData [phone_no = "+phoneNo+", password = "+password+", role = "+role+", name = "+name+", active = "+active+", otp = "+otp+", designation = "+designation+", time = "+time+", _id = "+id+", department = "+department+", email = "+email+"]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(password);
        dest.writeValue(name);
        dest.writeValue(designation);
        dest.writeValue(department);
        dest.writeValue(phoneNo);
        dest.writeValue(role);
        dest.writeValue(otp);
        dest.writeValue(time);
        dest.writeValue(active);
        dest.writeValue(id);
        dest.writeValue(email);
    }

    public int describeContents() {
        return 0;
    }
}