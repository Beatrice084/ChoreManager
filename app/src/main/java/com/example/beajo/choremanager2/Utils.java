package com.example.beajo.choremanager2;

import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by oguns on 11/29/2017.
 */

public class Utils {
    private DatabaseReference mDatabase;
    private static ArrayList<TaskItem> tasks;
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
}
