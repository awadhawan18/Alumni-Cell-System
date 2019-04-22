package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEventData implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("time_added")
    @Expose
    private String timeAdded;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Long v;
    public final static Parcelable.Creator<AddEventData> CREATOR = new Creator<AddEventData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddEventData createFromParcel(Parcel in) {
            return new AddEventData(in);
        }

        public AddEventData[] newArray(int size) {
            return (new AddEventData[size]);
        }

    }
            ;

    protected AddEventData(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.venue = ((String) in.readValue((String.class.getClassLoader())));
        this.datetime = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.timeAdded = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public AddEventData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getV() {
        return v;
    }

    public void setV(Long v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("title"+ title).append("venue"+ venue).append("datetime"+ datetime).append("description"+ description).append("timeAdded"+ timeAdded).append("id"+ id).append("v"+ v).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(venue);
        dest.writeValue(datetime);
        dest.writeValue(description);
        dest.writeValue(timeAdded);
        dest.writeValue(id);
        dest.writeValue(v);
    }

    public int describeContents() {
        return 0;
    }

}