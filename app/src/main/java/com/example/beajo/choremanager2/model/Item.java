package com.example.beajo.choremanager2.model;

/**
 * Created by saheed on 2017-11-27.
 */

public class Item {
    private String name;
    private  final int type;
    public Item(String name,int type){
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

}
