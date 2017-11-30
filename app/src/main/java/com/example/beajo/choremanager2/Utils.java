package com.example.beajo.choremanager2;

import android.util.Log;

import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;
import com.google.android.gms.tasks.Task;
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

    private static ArrayList<TaskItem> tasks = new ArrayList<>();
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

    public static ArrayList<TaskItem> getTasks(String uid){
        ArrayList<TaskItem> myTasks = new ArrayList<>();
        Iterator<TaskItem> iterator = tasks.iterator();
        while(iterator.hasNext()){
            TaskItem current = iterator.next();
            if (current.getPersonAssigned() == null){


            }
            else if(current.getPersonAssigned().equals(uid)){
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
                if(!binarySearchPerson(p)){
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

    public void downloadTasks(){
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TaskItem t = dataSnapshot.getValue(TaskItem.class);
                Log.d(TAG, "atsk childAdded");
                Collections.sort(tasks);
                if(!binarySearchPerson(t)){
                    tasks.add(t);
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
        DatabaseReference taskReference = mDatabase.child("Tasks");
        taskReference.addChildEventListener(childEventListener);
    }

    public void saveTask(TaskItem task){
        DatabaseReference peopleReference = mDatabase.child("Tasks").push();
        if(task.getUid() == null){
            Log.d(TAG, "is null");
            String key = peopleReference.getKey();
            task.setUid(key);
            peopleReference.setValue(task);

        }
        else {
            Log.d(TAG, "not null");
            mDatabase.child("Tasks").child(task.getUid()).setValue(task);

        }

    }

    public ArrayList<TaskItem> getTasks(){
        return tasks;
    }

    public ArrayList<Person> getPeople(){
        return people;
    }

    private boolean binarySearchPerson(Person p){
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

    private boolean binarySearchPerson(TaskItem t){
        int a = 0;
        int b = tasks.size()-1;

        while (b >= a){
            int mid = (a+b)/2;
            if(tasks.get(mid).getName().equals(t.getName())){
                return true;
            }
            if(tasks.get(mid).getName().compareTo(t.getName()) < 0){
                a = mid +1;
            }
            if(tasks.get(mid).getName().compareTo(t.getName()) > 0){
                b = mid -1;
            }
        }
        return false;
    }

    class Search<T>{

    }
}
