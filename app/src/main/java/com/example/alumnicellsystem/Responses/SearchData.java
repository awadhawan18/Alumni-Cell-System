package com.example.alumnicellsystem.Responses;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchData implements Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("__v")
    @Expose
    private Long v;
    public final static Parcelable.Creator<SearchData> CREATOR = new Creator<SearchData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchData createFromParcel(Parcel in) {
            return new SearchData(in);
        }

        public SearchData[] newArray(int size) {
            return (new SearchData[size]);
        }

    }
            ;

    protected SearchData(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.branch = ((String) in.readValue((String.class.getClassLoader())));
        this.v = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public SearchData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getV() {
        return v;
    }

    public void setV(Long v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "LoginData [name = "+name+", _id = "+id+"]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(branch);
        dest.writeValue(v);
    }

    public int describeContents() {
        return 0;
    }

}