package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    private String title;
    private String description;
    private String dateOfCreate;
    private int indexOfDescription;

    public Notes(String title, String description, String dateOfCreate) {
        this.title = title;
        this.description = description;
        this.dateOfCreate = dateOfCreate;
    }

    public Notes(int indexOfDescription, String title) {
        this.indexOfDescription = indexOfDescription;
        this.title = title;
    }

    protected Notes(Parcel in) {
        title = in.readString();
        description = in.readString();
        dateOfCreate = in.readString();
        indexOfDescription = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(dateOfCreate);
        dest.writeInt(indexOfDescription);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getIndexOfDescription() {
        return indexOfDescription;
    }
}