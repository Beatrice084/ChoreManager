package com.example.beajo.choremanager2.views;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beajo.choremanager2.Tool;
import com.example.beajo.choremanager2.ToolList;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.beajo.choremanager2.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ToolsActivity extends AppCompatActivity {
    EditText editToolName;
    Button buttonAddTool;
    ListView listViewTools;

    ArrayList<Item> tools;
    ArrayList<String> toolsString;
    ArrayAdapter<String> adapter;
    Utils utils;

    DatabaseReference databaseTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        databaseTools = FirebaseDatabase.getInstance().getReference("tools");
        editToolName = (EditText) findViewById(R.id.editToolName);
        listViewTools = (ListView) findViewById(R.id.listViewTools);
        buttonAddTool = (Button) findViewById(R.id.addButton);
        utils = new Utils();
        tools = utils.getTools();
        toolsString = new ArrayList<>();
        copyList();
        adapter = new ArrayAdapter<>(this, R.layout.item_layout, toolsString);
        utils.registerAdapter("ArrayAdapter", adapter);
        listViewTools.setAdapter(adapter);

        //adding an onclicklistener to button
        buttonAddTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTool();
            }
        });

        listViewTools.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item tool = tools.get(i);
                showUpdateDeleteDialog(tool, i);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
//        databaseTools.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //clearing the previous artist list
//                tools.clear();
//
//                //iterating through all the nodes
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //getting tool
//                    Item tool = postSnapshot.getValue(Item.class);
//                    //adding tool to the list
//                    tools.add(tool);
//                }
//
//                //creating adapter
//                ToolList toolsAdapter = new ToolList(ToolsActivity.this, tools);
//                //attaching adapter to the listview
//                listViewTools.setAdapter(toolsAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }


    private void showUpdateDeleteDialog(final Item item, final int i) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.tools_popup, null);
        dialogBuilder.setView(dialogView);

        final EditText editToolName = (EditText) dialogView.findViewById(R.id.groceryView);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateTool);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteTool);

        dialogBuilder.setTitle(item.getName());
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editToolName.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    item.setName(name);
                    b.dismiss();
                    utils.saveTool(item);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.deleteTool(item);
                toolsString.remove(i);
                adapter.notifyDataSetChanged();
                b.dismiss();
            }
        });
    }

    private void updateTool(String id, String name) {
        //getting the specified tool reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tools").child(id);
        //updating tool
        Item tool = new Item(name,0, null);
        dR.setValue(tool);
        Toast.makeText(getApplicationContext(), "Tool Updated", Toast.LENGTH_LONG).show();

    }

    private boolean deleteTool(String id) {
        //getting the specified tool reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tools").child(id);
        //removing prodct
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Tool Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addTool() {
        //getting the values to save
        String name = editToolName.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Tool
            String id = databaseTools.push().getKey();

            //creating an tool Object
            Item tool = new Item(name, 0, null);

            //Saving the Tool
            utils.saveTool(tool);
            toolsString.add(tool.getName());
            adapter.notifyDataSetChanged();
            //setting edittext to blank again
            editToolName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Tool added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    public void shoppingButtonClick(View view){//Starts Shopping activity
        Intent shoppingIntent = new Intent(getApplicationContext(), ShoppingActivity.class);
        startActivity(shoppingIntent);
        finish();
    }

    public void peopleButtonClick(View view){//Starts people_list activity
        Intent peopleIntent = new Intent(getApplicationContext(), PeopleActivity.class);
        startActivity(peopleIntent);
        finish();
    }

    public void tasksButtonClick(View view){//Starts ChoreList activity
        Intent tasksIntent = new Intent(getApplicationContext(), ActivityChoreList.class);
        startActivity(tasksIntent);
        finish();
    }

    public void otherButtonClick(View view){//Starts other activity
        Intent otherIntent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(otherIntent);
        finish();
    }

    public void copyList(){
        toolsString.clear();
        for(Item i : tools){
            toolsString.add(i.getName());
        }
    }
}
