
package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlumniUpdateResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private List<AlumniUpdateData> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AlumniUpdateResponse> CREATOR = new Creator<AlumniUpdateResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AlumniUpdateResponse createFromParcel(Parcel in) {
            return new AlumniUpdateResponse(in);
        }

        public AlumniUpdateResponse[] newArray(int size) {
            return (new AlumniUpdateResponse[size]);
        }

    }
            ;

    protected AlumniUpdateResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.data, (com.example.alumnicellsystem.Responses.AlumniUpdateData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AlumniUpdateResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<AlumniUpdateData> getData() {
        return data;
    }

    public void setData(List<AlumniUpdateData> data) {
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