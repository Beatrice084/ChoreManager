package com.example.beajo.choremanager2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saheed on 2017-11-27.
 */

public class Item implements Parcelable {
    private String name;
    private int type;

    public Item() {
    }

    public Item(String name, int type){
        this.name = name;
        this.type = type;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getType(){
        return this.type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.type);
    }

    protected Item(Parcel in) {
        this.name = in.readString();
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
