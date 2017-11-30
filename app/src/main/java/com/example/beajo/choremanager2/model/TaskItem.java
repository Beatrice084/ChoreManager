package com.example.beajo.choremanager2.model;

import java.util.ArrayList;

/**
 * Created by saheed on 2017-11-26.
 */

public class TaskItem {
    private String name;
    private Person personAssigned;
    private String note;
    private int status;
    private ArrayList<Item> equiptment;
    public TaskItem(String name, Person personAssigned, String note, ArrayList<Item> equiptment){
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

    public Person getPersonAssigned(){
        return this.personAssigned;
    }

    public void setPersonAssigned(Person personAssigned){
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
}
