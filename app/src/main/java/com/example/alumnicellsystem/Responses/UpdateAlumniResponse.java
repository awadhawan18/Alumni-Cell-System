package com.example.alumnicellsystem.Responses;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAlumniResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<UpdateAlumniResponse> CREATOR = new Creator<UpdateAlumniResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UpdateAlumniResponse createFromParcel(Parcel in) {
            return new UpdateAlumniResponse(in);
        }

        public UpdateAlumniResponse[] newArray(int size) {
            return (new UpdateAlumniResponse[size]);
        }

    }
            ;

    protected UpdateAlumniResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.data, (java.lang.Object.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UpdateAlumniResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
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