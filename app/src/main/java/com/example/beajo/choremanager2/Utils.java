package com.example.beajo.choremanager2;

import android.util.Log;

import com.example.beajo.choremanager2.model.Person;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by oguns on 11/29/2017.
 */

public class Utils {
    private DatabaseReference mDatabase;
    private static ArrayList<Person> people = new ArrayList<>();
    private static String TAG = Utils.class.getSimpleName();

    public Utils() {
       mDatabase  = FirebaseDatabase.getInstance().getReference();
    }

    public void addUser(Person p){
        DatabaseReference peopleReference = mDatabase.child("People/"+p.getUid());
        peopleReference.setValue(p);
    }

    public void getPeople(){
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Person p = dataSnapshot.getValue(Person.class);
                Log.d(TAG, "childAdded");
                people.add(p);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        DatabaseReference peopleReference = mDatabase.child("People");
        peopleReference.addChildEventListener(childEventListener);
    }
}
