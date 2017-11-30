package com.example.beajo.choremanager2;

import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.beajo.choremanager2.adapters.PersonAdapter;
import com.example.beajo.choremanager2.adapters.ShoppingAdapter;
import com.example.beajo.choremanager2.adapters.TaskAdapter;
import com.example.beajo.choremanager2.model.Item;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by oguns on 11/29/2017.
 */

public class Utils {

    private static ArrayList<TaskItem> tasks = new ArrayList<>();
    private static ArrayList<Item> tools = new ArrayList<>();
    private static ArrayList<Item> shoppingList = new ArrayList<>();
    private DatabaseReference mDatabase;
    private static ArrayList<Person> people = new ArrayList<>();
    private static String TAG = Utils.class.getSimpleName();
    private static Map<String, ArrayAdapter> adapters = new HashMap<>();

    public Utils() {
       mDatabase  = FirebaseDatabase.getInstance().getReference();
    }

    public void addUser(Person p){
        DatabaseReference peopleReference = mDatabase.child("People/"+p.getUid());
        peopleReference.setValue(p);
    }

    public static ArrayList<TaskItem> getTasks(String uid){
        Log.d(TAG, "Getting individual task");
        ArrayList<TaskItem> myTasks = new ArrayList<>();
        Iterator<TaskItem> iterator = tasks.iterator();
        while(iterator.hasNext()){
            TaskItem current = iterator.next();
          
            if(current.getUid()==null){
                Log.d(TAG, "Person is null");

            }
            else if(current.getUid().equals(uid)){
                Log.d(TAG, "task added");
                myTasks.add(current);
            }
            else{
                Log.d(TAG, "task is not the same");
            }
        }

        return myTasks;
    }

    public void downloadPeople(){
        Log.d(TAG, "download people");
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
                Person p = dataSnapshot.getValue(Person.class);
                int index = binarySearchPerson(p.getUid());
                if(index > -1){
                    people.remove(index);
                    people.add(index, p);
                    callAdapters(PersonAdapter.getKey());
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                TaskItem t = dataSnapshot.getValue(TaskItem.class);
                int index = binarySearchTask(t);
                if(index > -1){
                    people.remove(index);
                    callAdapters(PersonAdapter.getKey());
                }
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
        Log.d(TAG, "Download tasks");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TaskItem t = dataSnapshot.getValue(TaskItem.class);
                Log.d(TAG, "task childAdded");
                Collections.sort(tasks);
                if(binarySearchTask(t) == -1){
                    tasks.add(t);
                }
                callAdapters(TaskAdapter.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                TaskItem t = dataSnapshot.getValue(TaskItem.class);
                int index = binarySearchTask(t);
                if(index > -1){
                    tasks.remove(index);
                    callAdapters(TaskAdapter.getKey());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        DatabaseReference taskReference = mDatabase.child("Tasks");
        taskReference.addChildEventListener(childEventListener);
    }

    public void downloadTools(){
        Log.d(TAG, "Download tools");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item t = dataSnapshot.getValue(Item.class);
                Log.d(TAG, "tools childAdded");
                Collections.sort(tasks);
                if(binarySearchItem(t) == -1){
                    tools.add(t);
                }
                callAdapters(ToolList.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Item t = dataSnapshot.getValue(Item.class);
                int index = binarySearchItem(t);
                if(index > -1){
                    tools.remove(index);
                    callAdapters(ToolList.getKey());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        DatabaseReference taskReference = mDatabase.child("tools");
        taskReference.addChildEventListener(childEventListener);
    }

    public void downloadShoppingList(){
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item item = dataSnapshot.getValue(Item.class);
                //shoppingList.add();
                Collections.sort(shoppingList);
                if(binarySearchShoppingList(item) == -1){
                    shoppingList.add(item);
                }
                callAdapters(ShoppingAdapter.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Item t = dataSnapshot.getValue(Item.class);
                int index = binarySearchShoppingList(t);
                if(index > -1){
                    shoppingList.remove(index);
                    callAdapters(ShoppingAdapter.getKey());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        DatabaseReference shopRef = mDatabase.child("ShoppingList");
        shopRef.addChildEventListener(childEventListener);
    }

    public void saveTask(TaskItem task){
        DatabaseReference peopleReference;
        if(task.getId() == null){
            Log.d(TAG, "is null");
            peopleReference = mDatabase.child("Tasks").push();
            String key = peopleReference.getKey();
            task.setId(key);
            peopleReference.setValue(task);

        }
        else {
            Log.d(TAG, "not null");
            mDatabase.child("Tasks").child(task.getId()).setValue(task);

        }
    }

    public void saveShopItem(Item item){
        DatabaseReference peopleReference;
        if(item.getUid() == null){
            Log.d(TAG, "is null");
            peopleReference = mDatabase.child("ShoppingList").push();
            String key = peopleReference.getKey();
            item.setUid(key);
            peopleReference.setValue(item);

        }
        else {
            Log.d(TAG, "not null");
            mDatabase.child("ShoppingList").child(item.getId()).setValue(item);

        }
    }

    public void deleteItem(Item item){
        DatabaseReference ref = mDatabase.child("ShoppingList").child(item.getUid());
        ref.removeValue();
    }

    public void savePerson(Person person) {
        Log.d(TAG, "Saving person1");
        if (person == null) return;
        Log.d(TAG, "Saving person");
        mDatabase.child("People").child(person.getUid()).setValue(person);
    }
    public void updateScore(String uid, int type){
        int index = binarySearchPerson(uid);
        Log.d(TAG, "searching for person");
        if(index > -1){
            Person p = people.get(index);
            Log.d(TAG, "found person");
            if(type == 1){
                p.incPoints();
            }
            else {
                //p.decPoints();
            }
            savePerson(p);
        }

    }

    public void deleteTask(TaskItem task){
        DatabaseReference taskReference = mDatabase.child("Tasks").child(task.getId());
        taskReference.removeValue();
    }

    public void callAdapters(String key){
        ArrayAdapter a = adapters.get(key);
        if(a != null){
            a.notifyDataSetChanged();
        }
    }

    public ArrayList<TaskItem> getTasks(){
        return tasks;
    }

    public ArrayList<Person> getPeople(){
        return people;
    }

    public ArrayList<Item> getTools(){
        return tools;
    }

    public static ArrayList<Item> getShoppingList() {
        return shoppingList;
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

    public int binarySearchPerson(String uid){
        int a = 0;
        int b = people.size()-1;
        if(uid == null) return -1;
        while (b >= a){
            int mid = (a+b)/2;
            if(people.get(mid).getUid().equals(uid)){
                return mid;
            }
            if(people.get(mid).getUid().compareTo(uid) < 0){
                a = mid +1;
            }
            if(people.get(mid).getUid().compareTo(uid) > 0){
                b = mid -1;
            }
        }
        return -1;
    }

    private int binarySearchTask(TaskItem t){
        int a = 0;
        int b = tasks.size()-1;

        while (b >= a){
            int mid = (a+b)/2;
            if(tasks.get(mid).getName().equals(t.getName())){
                tasks.remove(mid);
                tasks.add(mid, t);
                return mid;
            }
            if(tasks.get(mid).getName().compareTo(t.getName()) < 0){
                a = mid +1;
            }
            if(tasks.get(mid).getName().compareTo(t.getName()) > 0){
                b = mid -1;
            }
        }
        return -1;
    }

    public int binarySearchShoppingList(Item t){
        int a = 0;
        int b = shoppingList.size()-1;

        while (b >= a){
            int mid = (a+b)/2;
            if(shoppingList.get(mid).getName().equals(t.getName())){
                shoppingList.remove(mid);
                shoppingList.add(mid, t);
                return mid;
            }
            if(shoppingList.get(mid).getName().compareTo(t.getName()) < 0){
                a = mid +1;
            }
            if(shoppingList.get(mid).getName().compareTo(t.getName()) > 0){
                b = mid -1;
            }
        }
        return -1;
    }

    public int binarySearchItem(String t){
        int a = 0;
        int b = tools.size()-1;

        while (b >= a){
            int mid = (a+b)/2;
            if(tools.get(mid).getName().equals(t)){
                return mid;
            }
            if(tools.get(mid).getName().compareTo(t) < 0){
                a = mid +1;
            }
            if(tools.get(mid).getName().compareTo(t) > 0){
                b = mid -1;
            }
        }
        return -1;
    }

    public int binarySearchItem(ArrayList<String> tools,String t){
        if(tools == null) return -1;
        int a = 0;
        int b = tools.size()-1;

        while (b >= a){
            int mid = (a+b)/2;
            if(tools.get(mid).equals(t)){
                return mid;
            }
            if(tools.get(mid).compareTo(t) < 0){
                a = mid +1;
            }
            if(tools.get(mid).compareTo(t) > 0){
                b = mid -1;
            }
        }
        return -1;
    }

    public int binarySearchItem(Item t){
    int a = 0;
    int b = tools.size()-1;

    while (b >= a){
        int mid = (a+b)/2;
        if(tools.get(mid).getName().equals(t.getName())){
            tools.remove(mid);
            tools.add(mid, t);
            return mid;
        }
        if(tools.get(mid).getName().compareTo(t.getName()) < 0){
            a = mid +1;
        }
        if(tools.get(mid).getName().compareTo(t.getName()) > 0){
            b = mid -1;
        }
    }
    return -1;
}

    public void registerAdapter(String key, ArrayAdapter adapter){
        adapters.put(key, adapter);
    }

}