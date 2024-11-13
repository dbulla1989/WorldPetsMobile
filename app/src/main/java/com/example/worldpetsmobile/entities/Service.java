package com.example.worldpetsmobile.entities;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

public class Service implements Parcelable {

    private Date date;
    private String name;
    private String location;
    private String specialist;

    public Service(String name, Date date, String location, String specialist) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.specialist = specialist;
    }

    // Constructor que recibe un Parcel
    protected Service(Parcel in) {
        name = in.readString();
        location = in.readString();
        specialist = in.readString();
        long dateMillis = in.readLong();
        date = (dateMillis == -1) ? null : new Date(dateMillis);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(specialist);
        dest.writeLong(date != null ? date.getTime() : -1);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    // Getters y setters
    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}