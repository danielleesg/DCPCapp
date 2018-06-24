package com.example.daniellee.dcpcapp;

import android.os.Bundle;
import android.app.Activity;

public class AddEvent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }

}

public class Event implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
}

public void writeToParcel(Parcel dest, int flags) {
        // TODO add things here
        dest.writeString(this.title);
}

public Event(Parcel parcel){
        this.title = parcel.readString()
        // TODO continue adding lines
}

public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>(){
    @Override
    public Event createFromParcel(Parcel parcel) {
    return new Event(parcel);
    }

    @Override
    public Event[] newArray(int size) {
        return new Event[0];
    }
};

