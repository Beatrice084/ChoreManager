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
    private ArrayList<Item> equipment;
    String uid = null;
    String id = null;


    public TaskItem() {}


    public TaskItem(String name, String personAssigned, String note, ArrayList<Item> equipment){
        this.name = name;
        this.personAssigned = personAssigned;
        this.note = note;
        this.equipment = equipment;

    }
    public TaskItem(String name, String personAssigned, String note){
        this.name = name;
        this.personAssigned = personAssigned;
        this.note = note;
        this.equipment = new ArrayList<Item>();

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

    public ArrayList<Item> getEquipment() {
        return equipment;
    }

    public void addEquipment(Item item) {
        if(this.equipment == null){
            this.equipment = new ArrayList<>();
        }
        this.equipment.add(item);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEquipment(ArrayList<Item> equipment) {
        this.equipment = equipment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        dest.writeTypedList(this.equipment);
        dest.writeString(this.uid);
        dest.writeString(this.id);
    }

    protected TaskItem(Parcel in) {
        this.name = in.readString();
        this.personAssigned = in.readString();
        this.note = in.readString();
        this.status = in.readInt();
        this.equipment = in.createTypedArrayList(Item.CREATOR);

        this.uid = in.readString();
        this.id = in.readString();
    }

    public static final Creator<TaskItem> CREATOR = new Creator<TaskItem>() {
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
