
package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlumniLoginResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private AlumniLoginData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AlumniLoginResponse> CREATOR = new Creator<AlumniLoginResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AlumniLoginResponse createFromParcel(Parcel in) {
            return new AlumniLoginResponse(in);
        }

        public AlumniLoginResponse[] newArray(int size) {
            return (new AlumniLoginResponse[size]);
        }

    }
            ;

    protected AlumniLoginResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        this.data = ((AlumniLoginData) in.readValue((AlumniLoginData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AlumniLoginResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public AlumniLoginData getData() {
        return data;
    }

    public void setData(AlumniLoginData data) {
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