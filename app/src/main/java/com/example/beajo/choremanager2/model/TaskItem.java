package com.example.beajo.choremanager2.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by saheed on 2017-11-26.
 */

public class TaskItem implements Comparable<TaskItem>,Parcelable {
    private String name;
    private String personAssigned;
    private String note;
    private int status;
    private ArrayList<Item> equiptment;

    public TaskItem() {
    }

    public TaskItem(String name, String personAssigned, String note, ArrayList<Item> equiptment){
        this.name = name;
        this.personAssigned = personAssigned;
        this.note = note;
        this.equiptment = equiptment;

    }


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPersonAssigned(){
        return this.personAssigned;
    }

    public void setPersonAssigned(String personAssigned){
        this.personAssigned = personAssigned;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public ArrayList<Item> getEquiptment() {
        return equiptment;
    }

    public void setEquiptment(ArrayList<Item> equiptment) {
        this.equiptment = equiptment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public int compareTo(@NonNull TaskItem o) {
        return name.compareTo(o.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.personAssigned);
        dest.writeString(this.note);
        dest.writeInt(this.status);
        dest.writeList(this.equiptment);
    }

    protected TaskItem(Parcel in) {
        this.name = in.readString();
        this.personAssigned = in.readString();
        this.note = in.readString();
        this.status = in.readInt();
        this.equiptment = new ArrayList<Item>();
        in.readList(this.equiptment, Item.class.getClassLoader());
    }

    public static final Parcelable.Creator<TaskItem> CREATOR = new Parcelable.Creator<TaskItem>() {
        @Override
        public TaskItem createFromParcel(Parcel source) {
            return new TaskItem(source);
        }

        @Override
        public TaskItem[] newArray(int size) {
            return new TaskItem[size];
        }
    };
}
