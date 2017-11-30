package com.example.beajo.choremanager2.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by saheed on 2017-11-27.
 */

public class Item implements Parcelable, Comparable<Item> {
    private String name;
    private int type;
    private String id;
    private String uid;

    public Item() {
    }

    public Item(String name, int type, String id) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeString(this.id);
        dest.writeString(this.uid);
    }

    protected Item(Parcel in) {
        this.name = in.readString();
        this.type = in.readInt();
        this.id = in.readString();
        this.uid = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int compareTo(@NonNull Item o) {
        return name.compareTo(o.getName());
    }
}
