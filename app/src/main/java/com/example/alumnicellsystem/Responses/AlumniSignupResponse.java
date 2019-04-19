package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlumniSignupResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private AlumniSignupData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AlumniSignupResponse> CREATOR = new Creator<AlumniSignupResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AlumniSignupResponse createFromParcel(Parcel in) {
            return new AlumniSignupResponse(in);
        }

        public AlumniSignupResponse[] newArray(int size) {
            return (new AlumniSignupResponse[size]);
        }

    }
            ;

    protected AlumniSignupResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        this.data = ((AlumniSignupData) in.readValue((AlumniSignupData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AlumniSignupResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public AlumniSignupData getAlumniSignupData() {
        return data;
    }

    public void setAlumniSignupData(AlumniSignupData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("status"+ status).append("data"+ data).append("message"+ message).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}