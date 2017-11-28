package com.example.beajo.choremanager2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

/**
 * Created by saheed on 2017-11-27.
 */


// make this a singleton class

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "choreApp.db";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating table for the list of family members
        String CREATE_PERSON_TABLE = "CREATE TABLE Person (" +
                "name TEXT PRIMARY KEY UNIQUE KEY NOT NULL)";


        // creating task table
        String CREATE_TASK_TABLE = "CREATE TABLE Task (" +
                "taskName TEXT NOT NULL, " +
                "personAssigned TEXT NOT NULL, " +
                "taskNote TEXT," +
                // have to figure out how to relate equipment to tasks ""+
                "CONSTRAINT personTask FOREIGN KEY (personAssigned) REFERENCES Person (name))";



        // creating cupboard and fride table
        String CREATE_CUPBOARDANDFRIDGEITEM_TABLE ="CREATE TABLE Cupboardandfridgeitem (" +
                "item TEXT NOT NULL," +
                "CONSTRAINT personCupboard FOREIGN KEY (person) REFERENCES Person (name))";

        // creating table for shopping items
        String CREATE_SHOPPINGITEM_TABLE = "CREATE TABLE Shoppingitem (" +
                "person TEXT NOT NULL," +
                "shoppingitem TEXT NOT NULL," +
                "CONSTRAINT personShoppingitem FOREIGN KEY (person) REFERENCES Person (name)";

        // creating table for tools
        String CREATE_TOOLS_TABLE ="CREATE TABLE Tool (item TEXT PRIMARY KEY UNIQUE KEY NOT NULL)";

        db.execSQL(CREATE_PERSON_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
        db.execSQL(CREATE_CUPBOARDANDFRIDGEITEM_TABLE);
        db.execSQL(CREATE_SHOPPINGITEM_TABLE);
        db.execSQL(CREATE_TOOLS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
    }

    // controler methods for table person
    public void addPerson(Person person) {

        ContentValues values = new ContentValues();
        values.put("name", person.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Person", null, values);

        db.close();

    }

    public Person findPerson(String name) {
        String query = "Select * FROM Person WHERE name = \"" +name+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Person person = null;
        if (cursor.moveToFirst()) {
            person = new Person(cursor.getString(0));
            cursor.close();
        }
        db.close();
        return person;
    }

    public boolean deletePerson(String name) {
        boolean result = false;

        String query = "Select * FROM Person WHERE name = \"" +name+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String nameToDelete = cursor.getString(0);
            db.delete("Person", "name = " + nameToDelete, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    // controller methods for table task
    public void addTask(Person person,TaskItem task) {

        ContentValues values = new ContentValues();
        values.put("taskName",task.getName());
        values.put("personAssigned", person.getName());
        values.put("taskNote",task.getNote());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Task", null, values);

        db.close();

    }

    public ArrayList<TaskItem> findTask(Person person) {
        String query = "Select * FROM Task WHERE personAssigned = \"" +person.getName()+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<TaskItem> result = new ArrayList<TaskItem>();

        for (int i=0 ;i<cursor.getCount();i++){
            if(cursor.moveToPosition(i)){
                result.add(new TaskItem(cursor.getString(0),new Person( cursor.getString(1)),cursor.getString(2),null));
            }
        }
        cursor.close();
        db.close();
        return result;
    }

    public ArrayList<TaskItem> findTask() {
        String query = "Select * FROM Task ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<TaskItem> result = new ArrayList<TaskItem>();

        for (int i=0 ;i<cursor.getCount();i++){
            if(cursor.moveToPosition(i)){
                result.add(new TaskItem(cursor.getString(0),new Person( cursor.getString(1)),cursor.getString(2),null));
            }
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteTask(String taskName) {
        boolean result = false;

        String query = "Select * FROM Task WHERE taskName = \"" +taskName+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String taskToDelete = cursor.getString(1);
            db.delete("Task", "taskName = " + taskToDelete, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    // controller methods for cupboard item
    public void addCupboardItem(Item item) {

        ContentValues values = new ContentValues();
        values.put("item", item.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Cupboardandfridgeitem", null, values);

        db.close();

    }

    public ArrayList<Item> findCupboardItem() {
        String query = "Select * FROM Cupboardandfridgeitem" ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Item> result = new ArrayList<Item>();


        for (int i=0 ;i<cursor.getCount();i++){
            if(cursor.moveToPosition(i)){
                result.add(new Item(cursor.getString(0)));
            }
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteCupboardItem(String item) {
        boolean result = false;

        String query = "Select * FROM Cupboardandfridgeitem WHERE item = \"" +item+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String itemToDelete = cursor.getString(0);
            db.delete("Cupboardandfridgeitem", "item = " + itemToDelete, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    // controller methods for shopping items
    public void addShoppingItem(Person person,Item item) {

        ContentValues values = new ContentValues();
        values.put("person", person.getName());
        values.put("item", item.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Shoppingitem", null, values);

        db.close();

    }
    public ArrayList<Item> findShoppingItems(Person person){
        String query = "Select * FROM Shoppingitem WHERE person =  \"" +person.getName()+ "\""  ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Item> result = new ArrayList<Item>();


        for (int i=0 ;i<cursor.getCount();i++){
            if(cursor.moveToPosition(i)){
                result.add(new Item(cursor.getString(1)));
            }
        }
        db.close();
        cursor.close();
        return result;
    }
    public boolean deleteShoppingItem(Item item) {
        boolean result = false;

        String query = "Select * FROM Task WHERE name = \"" +item.getName()+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String taskToDelete = cursor.getString(1);
            db.delete("Task", "taskName = " + taskToDelete, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    // controller methods for tools
    public void addTool(Item item) {

        ContentValues values = new ContentValues();
        values.put("item", item.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Tool", null, values);

        db.close();

    }
    public ArrayList<Item> findTools() {
        String query = "Select * FROM Tool" ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Item> result = new ArrayList<Item>();


        for (int i=0 ;i<cursor.getCount();i++){
            if(cursor.moveToPosition(i)){
                result.add(new Item(cursor.getString(0)));
            }
        }
        cursor.close();
        db.close();
        return result;
    }
    public boolean deleteTool(String item) {
        boolean result = false;

        String query = "Select * FROM Tool WHERE item = \"" +item+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String itemToDelete = cursor.getString(0);
            db.delete("Tool", "item = " + itemToDelete, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }
}
