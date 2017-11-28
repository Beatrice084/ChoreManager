package com.example.beajo.choremanager2;

/**
 * Created by saheed on 2017-11-26.
 */

public class TaskItem {
    private String name;
    private Person personAssigned;
    private String note;
    private Item[] equiptment;
    public TaskItem(String name, Person personAssigned, String note, Item[] equiptment){
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
    public Item[] getEquiptment(){
        return this.equiptment;
    }
    public void setEquiptment(Item[] equiptment){
        this.equiptment = equiptment;
    }
}
