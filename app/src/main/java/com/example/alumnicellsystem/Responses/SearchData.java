package com.example.alumnicellsystem.Responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchData implements Parcelable
{

    @SerializedName("item")
    @Expose
    private SearchItem searchItem;
    @SerializedName("score")
    @Expose
    private Long score;
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
        this.searchItem = ((SearchItem) in.readValue((SearchItem.class.getClassLoader())));
        this.score = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public SearchData() {
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("item"+ searchItem).append("score"+ score).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(searchItem);
        dest.writeValue(score);
    }

    public int describeContents() {
        return 0;
    }

}