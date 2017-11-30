package com.example.beajo.choremanager2.model;

/**
 * Created by saheed on 2017-11-27.
 */

public class Item {
    private String name;
    public Item(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
