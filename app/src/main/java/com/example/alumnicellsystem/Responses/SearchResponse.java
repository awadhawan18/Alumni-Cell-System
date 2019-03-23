package com.example.alumnicellsystem.Responses;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Long status;
    @SerializedName("data")
    @Expose
    private List<SearchData> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchResponse createFromParcel(Parcel in) {
            return new SearchResponse(in);
        }

        public SearchResponse[] newArray(int size) {
            return (new SearchResponse[size]);
        }

    }
            ;

    protected SearchResponse(Parcel in) {
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        in.readList(this.data, (SearchData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SearchResponse() {
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<SearchData> getData() {
        return data;
    }

    public void setData(List<SearchData> data) {
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
        return new StringBuilder().append("\nstatus "+ status).append("\ndata "+ data).append("\nmessage"+ message).toString();
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