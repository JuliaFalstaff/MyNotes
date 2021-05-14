package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    private String title;
    private String description;
    private String dateOfCreate;
    private int index;

    public Notes(String title, String description, String dateOfCreate) {
        this.title = title;
        this.description = description;
        this.dateOfCreate = dateOfCreate;
    }

    public Notes(int index, String title) {
        this.index = index;
        this.title = title;
    }


    protected Notes(Parcel in) {
        title = in.readString();
        description = in.readString();
        dateOfCreate = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTitle());
        dest.writeString(getDescription());
        dest.writeString(getDateOfCreate());
        dest.writeInt(getIndex());
    }
}