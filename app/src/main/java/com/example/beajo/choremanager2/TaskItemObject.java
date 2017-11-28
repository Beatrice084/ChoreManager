package com.example.beajo.choremanager2;

/**
 * Created by saheed on 2017-11-26.
 */

public class TaskItemObject {
    private String name;
    private PersonObject personAssigned;
    private String note;
    private ItemObject[] equiptment;
    public TaskItemObject(String name,PersonObject personAssigned,String note,ItemObject[] equiptment){
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
    public PersonObject getPersonAssigned(){
        return this.personAssigned;
    }
    public void setPersonAssigned(PersonObject personAssigned){
        this.personAssigned = personAssigned;
    }
    public String getNote(){
        return this.note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public ItemObject[] getEquiptment(){
        return this.equiptment;
    }
    public void setEquiptment(ItemObject[] equiptment){
        this.equiptment = equiptment;
    }
}
