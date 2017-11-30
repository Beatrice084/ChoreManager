package com.example.beajo.choremanager2;

import android.util.Log;

import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;


/**
 * Created by oguns on 11/29/2017.
 */

public class Utils {

    private static ArrayList<TaskItem> tasks;
    private DatabaseReference mDatabase;
    private static ArrayList<Person> people = new ArrayList<>();
    private static String TAG = Utils.class.getSimpleName();

    public Utils() {
       mDatabase  = FirebaseDatabase.getInstance().getReference();
       tasks = null;
    }

    public void addUser(Person p){
        DatabaseReference peopleReference = mDatabase.child("People/"+p.getUid());
        peopleReference.setValue(p);
    }

    public static ArrayList<TaskItem> getTasks(String uid){
        ArrayList<TaskItem> myTasks = new ArrayList<>();
        Iterator<TaskItem> iterator = tasks.iterator();
        while(iterator.hasNext()){
            TaskItem current = iterator.next();
            if(current.getPersonAssigned().getUid().equals(uid)){
                myTasks.add(current);
            }
        }

        return myTasks;
    }

    public void downloadPeople(){
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Person p = dataSnapshot.getValue(Person.class);
                Log.d(TAG, "childAdded");
                Collections.sort(people);
                if(!binarySearch(p)){
                    people.add(p);
                }
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

    public ArrayList<Person> getPeople(){
        return people;
    }

    private boolean binarySearch(Person p){
        int a = 0;
        int b = people.size()-1;

        while (b >= a){
            int mid = (a+b)/2;
            if(people.get(mid).getUid().equals(p.getUid())){
                return true;
            }
            if(people.get(mid).getUid().compareTo(p.getUid()) < 0){
                a = mid +1;
            }
            if(people.get(mid).getUid().compareTo(p.getUid()) > 0){
                b = mid -1;
            }
        }
        return false;
    }
}
