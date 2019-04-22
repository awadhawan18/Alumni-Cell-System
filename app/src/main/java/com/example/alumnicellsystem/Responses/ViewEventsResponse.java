package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewEventsResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private List<ViewEventsData> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<ViewEventsResponse> CREATOR = new Creator<ViewEventsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ViewEventsResponse createFromParcel(Parcel in) {
            return new ViewEventsResponse(in);
        }

        public ViewEventsResponse[] newArray(int size) {
            return (new ViewEventsResponse[size]);
        }

    }
            ;

    protected ViewEventsResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.data, (com.example.alumnicellsystem.Responses.ViewEventsData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ViewEventsResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<ViewEventsData> getData() {
        return data;
    }

    public void setData(List<ViewEventsData> data) {
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
        dest.writeList(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}