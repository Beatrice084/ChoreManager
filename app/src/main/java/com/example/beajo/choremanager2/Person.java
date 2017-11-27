package com.example.beajo.choremanager2;

/**
 * Created by oguns on 11/27/2017.
 */

public class Person {
    String name, title;
    int image;

    public Person() {
    }

    public Person(String name,  int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
