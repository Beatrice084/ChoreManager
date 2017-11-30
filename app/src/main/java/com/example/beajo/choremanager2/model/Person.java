package com.example.beajo.choremanager2.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by oguns on 11/27/2017.
 */

public class Person implements Comparable<Person>,Parcelable {
    String name, uid, email;
    int gender, points;

    public Person() {
    }

    public Person(String name, String uid, String email, int gender) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPoints(){return points;}

    public void incPoints(){this.points++;}

    @Override
    public int compareTo(@NonNull Person o) {
        return uid.compareTo(o.getUid());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.uid);
        dest.writeString(this.email);
        dest.writeInt(this.gender);
        dest.writeInt(this.points);
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.uid = in.readString();
        this.email = in.readString();
        this.gender = in.readInt();
        this.points = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
