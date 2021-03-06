package com.example.daniellee.dcpcapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

    private String date;
    private String location;
    private String title;

    private String url;
    private String host;
    private String street;
    private String suburb;
    private String city;

    private String owner;

    public Event (String title, String location, String date ){
        this.title= title;
        this.location = location;
        this.date= date;
    }

    @Override
    public int describeContents () { return 0; }

    public void writeToParcel (Parcel dest, int flags) {
        //TODO Add things here
        dest.writeString(this.title);
        dest.writeString(this.location);
        dest.writeString(this.date);
//        dest.writeString(this.url);
        //...

    }

    public Event(Parcel parcel) {
        this.title = parcel.readString();
        // TODO continue to adding lines
        this.location = parcel.readString();
        this.date = parcel.readString();
        //...
    }

    private static final Parcelable.Creator <Event> CREATOR = new Parcelable.Creator <Event>() {
        @Override
        public Event createFromParcel(Parcel parcel) {
            return new Event(parcel);
        }

        @Override
        public Event[] newArray(int i) {
            return new Event[0];
        }
    };

    public String toString(){
        return this.title;
    }
}