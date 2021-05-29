package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.SplittableRandom;

public class Note implements Parcelable {
    private String id;
    private String title;
    private String subTitle;
    private String description;
    private int indexOfDescription;
    private int picture;
    private Date date;

    public Note(String title, String subTitle, int picture) {
        this.title = title;
        this.subTitle = subTitle;
        this.picture = picture;
    }

    public Note(int indexOfDescription, String title) {
        this.indexOfDescription = indexOfDescription;
        this.title = title;
    }

    protected Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        subTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(subTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getPicture() {
        return picture;
    }

    public int getIndexOfDescription() {
        return indexOfDescription;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}