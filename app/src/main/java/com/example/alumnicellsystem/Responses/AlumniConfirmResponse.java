package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlumniConfirmResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AlumniConfirmResponse> CREATOR = new Creator<AlumniConfirmResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AlumniConfirmResponse createFromParcel(Parcel in) {
            return new AlumniConfirmResponse(in);
        }

        public AlumniConfirmResponse[] newArray(int size) {
            return (new AlumniConfirmResponse[size]);
        }

    }
            ;

    protected AlumniConfirmResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        this.data = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AlumniConfirmResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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