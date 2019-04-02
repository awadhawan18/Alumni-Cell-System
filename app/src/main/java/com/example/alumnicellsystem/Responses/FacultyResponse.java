package com.example.alumnicellsystem.Responses;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FacultyResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private List<FacultyData> data = new ArrayList<>();
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<FacultyResponse> CREATOR = new Creator<FacultyResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FacultyResponse createFromParcel(Parcel in) {
            return new FacultyResponse(in);
        }

        public FacultyResponse[] newArray(int size) {
            return (new FacultyResponse[size]);
        }

    }
            ;

    protected FacultyResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.data, (FacultyData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public FacultyResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<FacultyData> getData() {
        return data;
    }

    public void setData(List<FacultyData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}