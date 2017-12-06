package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.adapters.ShoppingAdapter;
import com.example.beajo.choremanager2.model.Item;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    ListView list;
    ShoppingAdapter adapter;
    EditText groceryView;
    Button addButton;
    Utils util;
    ArrayList<Item> items;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        util = new Utils();
        items = util.getShoppingList();
        names = new ArrayList<>();

        groceryView = (EditText)findViewById(R.id.groceryView);
        addButton = (Button)findViewById(R.id.addButton);
        list = (ListView)findViewById(R.id.list);
        adapter = new ShoppingAdapter(this, items);
        list.setAdapter(adapter);
        util.registerAdapter(ShoppingAdapter.getKey(), adapter);
    }

    public void peopleButtonClick(View view){//Starts people_list activity
        Intent peopleIntent = new Intent(getApplicationContext(), PeopleActivity.class);
        startActivity(peopleIntent);
        finish();
    }

    public void resourceButtonClick(View view){//Starts other activity
        Intent resourceIntent = new Intent(getApplicationContext(), ToolsActivity.class);
        startActivity(resourceIntent);
        finish();
    }

    public void tasksButtonClick(View view){//Starts ChoreList activity
        Intent tasksIntent = new Intent(getApplicationContext(), ActivityChoreList.class);
        startActivity(tasksIntent);
        finish();
    }

    public void addButtonClick(View view){
        save();
    }

    public ArrayList<String> getNames(){

        for (Item i : items){
            names.add(i.getName());
        }
        return names;
    }

    public void save(){
        String title = groceryView.getText().toString();
        Item item = new Item();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "Invalid entry", Toast.LENGTH_SHORT).show();
        }
        else {
            item.setName(title);
            item.setType(1);
            util.saveShopItem(item);
        }
    }

}
