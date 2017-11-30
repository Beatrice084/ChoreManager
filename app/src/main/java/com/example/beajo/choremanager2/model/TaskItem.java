package com.example.beajo.choremanager2.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by saheed on 2017-11-26.
 */

public class TaskItem implements Comparable<TaskItem> {
    private String name;
    private String personAssigned;
    private String note;
    private int status;
    private ArrayList<Item> equipment;

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
        this.equipment.add(item);
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
}
