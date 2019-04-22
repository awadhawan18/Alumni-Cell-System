
package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEventResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private AddEventData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AddEventResponse> CREATOR = new Creator<AddEventResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddEventResponse createFromParcel(Parcel in) {
            return new AddEventResponse(in);
        }

        public AddEventResponse[] newArray(int size) {
            return (new AddEventResponse[size]);
        }

    }
            ;

    protected AddEventResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        this.data = ((AddEventData) in.readValue((AddEventData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AddEventResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public AddEventData getAddEventData() {
        return data;
    }

    public void setAddEventData(AddEventData data) {
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