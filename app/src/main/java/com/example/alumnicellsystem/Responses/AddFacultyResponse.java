package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFacultyResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private AddFacultyData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AddFacultyResponse> CREATOR = new Creator<AddFacultyResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddFacultyResponse createFromParcel(Parcel in) {
            return new AddFacultyResponse(in);
        }

        public AddFacultyResponse[] newArray(int size) {
            return (new AddFacultyResponse[size]);
        }

    }
            ;

    protected AddFacultyResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        this.data = ((AddFacultyData) in.readValue((AddFacultyData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AddFacultyResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public AddFacultyData getAddFacultyData() {
        return data;
    }

    public void setAddFacultyData(AddFacultyData data) {
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