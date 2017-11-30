package com.example.beajo.choremanager2.model;

import android.support.annotation.NonNull;

/**
 * Created by oguns on 11/27/2017.
 */

public class Person implements Comparable<Person> {
    String name, uid, email;
    int gender, points;

    public Person() {
    }

    public Person(String name, String uid, String email, int gender) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(@NonNull Person o) {
        return uid.compareTo(o.getUid());
    }
}
