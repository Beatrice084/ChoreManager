package com.example.beajo.choremanager2;

import com.example.beajo.choremanager2.model.Person;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by oguns on 11/29/2017.
 */

public class Utils {
    private DatabaseReference mDatabase;

    public Utils() {
       mDatabase  = FirebaseDatabase.getInstance().getReference();
    }

    public void addUser(Person p){
        DatabaseReference peopleReference = mDatabase.child("People/"+p.getUid());
        peopleReference.setValue(p);
    }
}
