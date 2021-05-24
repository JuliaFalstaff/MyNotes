package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String title;
    private String subTitle;
    private String description;
    private String dateOfCreate;
    private int indexOfDescription;
    private int picture;

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
        dateOfCreate = in.readString();
        indexOfDescription = in.readInt();
        subTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(dateOfCreate);
        dest.writeInt(indexOfDescription);
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

    public String getDateOfCreate() {
        return dateOfCreate;
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
}