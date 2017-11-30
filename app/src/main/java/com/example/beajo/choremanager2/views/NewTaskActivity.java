package com.example.beajo.choremanager2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.model.Item;
import com.example.beajo.choremanager2.model.TaskItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TaskItem task;
    ArrayList<String> items;
    ArrayList<String> itemB;
    ArrayList<Item> realItems;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<String> adapterB;
    Spinner spinner;
    ListView listView;
    TextView nameView, descriptionView;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Utils util;
    ArrayList<Item> tools;
    private final String TAG = NewTaskActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Bundle b = getIntent().getExtras();
        nameView = (TextView)findViewById(R.id.newTaskName);
        descriptionView = (TextView)findViewById(R.id.newNote);

        util = new Utils();

        items = new ArrayList<>();
        itemB = new ArrayList<>();
        realItems = new ArrayList<>();
        tools = util.getTools();
        addItems();

        listView = (ListView) findViewById(R.id.equipment_grid);
        spinner = (Spinner)findViewById(R.id.equipment_spinner);
        spinner.setOnItemSelectedListener(this);
        adapter = new ArrayAdapter(this, R.layout.item_layout, items);
        adapterB = new ArrayAdapter(this, R.layout.item_layout, itemB);
        spinner.setAdapter(adapter);
        listView.setAdapter(adapterB);

        try {
            task = b.getParcelable("task");
        }catch (NullPointerException e){
            task = null;
        }

        if(task == null){
            task = new TaskItem();
        }
        else {
            updateView();
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Long click");
                int index = util.binarySearchItem(itemB.get(position));
                if(index > -1){
                    Item item = tools.get(index);
                    itemB.remove(item.getName());
                    realItems.remove(item);
                    adapterB.notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    public void updateView(){
        nameView.setText(task.getName());
        descriptionView.setText(task.getNote());
        for (Item i : task.getEquipment()){
            itemB.add(i.getName());
            adapterB.notifyDataSetChanged();
        }
    }

    private void addItems(){
        items.clear();
        for (int i = 0; i < tools.size(); i ++){
            items.add(tools.get(i).getName());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(util.binarySearchItem(itemB, tools.get(position).getName()) == -1) {
            itemB.add(items.get(position));
            realItems.add(tools.get(position));
            adapterB.notifyDataSetChanged();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_button:
                if(verify()){
                    util.saveTask(task);
                    Toast.makeText(this, "Task saved", Toast.LENGTH_LONG).show();
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean verify(){
        String name = nameView.getText().toString();
        String note = descriptionView.getText().toString();
        task.setName(name);
        task.setNote(note);
        //task.setEquipment(realItems);
        if(task.getEquipment() != null)
        task.getEquipment().clear();
        for(Item i : realItems){
            task.addEquipment(i);
        }
        task.setStatus(0);
        boolean state = true;
        if(TextUtils.isEmpty(name)){
            nameView.setError("Please fill name");
            state = false;
        }
        if(TextUtils.isEmpty(note)){
            descriptionView.setError("please enter description");
            state = false;
        }
        return state;
    }
}
