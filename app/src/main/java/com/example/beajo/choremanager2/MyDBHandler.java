package com.example.beajo.choremanager2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.beajo.choremanager2.model.Item;
import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;

import java.util.ArrayList;

/**
 * Created by saheed on 2017-11-27.
 */


// make this a singleton class

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TAG = MyDBHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "choreApp.db";
    private static final String PERSON_TABLE_NAME = "Person";
    private static final String TASK_TABLE_NAME = "Task";
    private static final String CUPBOARDANDFRIDGE_TABLE_NAME = "Cupboardandfridgeitem";
    private static final String SHOPPING_TABLE_NAME = "Shoppingitem";
    private static final String TOOL_TABLE_NAME = "Tool";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating table for the list of family members
        String CREATE_PERSON_TABLE = "CREATE TABLE " + PERSON_TABLE_NAME +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL, image INTEGER);";


        // creating task table
        String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE_NAME +
                "(taskName TEXT NOT NULL, " +
                "personAssigned TEXT NOT NULL, " +
                "taskNote TEXT," +
                // have to figure out how to relate equipment to tasks ""+
                "CONSTRAINT personTask FOREIGN KEY (personAssigned) REFERENCES Person (name))";

        // creating cupboard and fride table
        String CREATE_CUPBOARDANDFRIDGEITEM_TABLE ="CREATE TABLE " +  CUPBOARDANDFRIDGE_TABLE_NAME +
                "(item TEXT NOT NULL," +
                "CONSTRAINT personCupboard FOREIGN KEY (person) REFERENCES Person (name))";

        // creating table for shopping items
        String CREATE_SHOPPINGITEM_TABLE = "CREATE TABLE " + SHOPPING_TABLE_NAME +
                "(person TEXT NOT NULL," +
                "shoppingitem TEXT NOT NULL," +
                "CONSTRAINT personShoppingitem FOREIGN KEY (person) REFERENCES Person (name)";

        // creating table for tools
        String CREATE_TOOLS_TABLE ="CREATE TABLE " + TOOL_TABLE_NAME + " (item TEXT PRIMARY KEY UNIQUE KEY NOT NULL)";

        db.execSQL(CREATE_PERSON_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
//        db.execSQL(CREATE_CUPBOARDANDFRIDGEITEM_TABLE);
//        db.execSQL(CREATE_SHOPPINGITEM_TABLE);
//        db.execSQL(CREATE_TOOLS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


 
}
