package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.model.Item;
import com.example.beajo.choremanager2.model.TaskItem;

import java.util.ArrayList;

public class TaskIndividualActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ListView requiredEquipment;
    private ImageView personIcon;
    TextView personName;
    Button release;
    Spinner spinner;
    ArrayList<String> items;
    ArrayAdapter<CharSequence> spinnerAdapter;
    ArrayAdapter listAdapter;
    TaskItem t;
    Utils util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_individual);
        util = new Utils();
        Bundle bundle = getIntent().getExtras();
        t = bundle.getParcelable("Task");
        items = new ArrayList<>();
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.status, R.layout.item_layout);
        listAdapter = new ArrayAdapter(this, R.layout.item_layout, items);


        personName = (TextView) findViewById(R.id.personName);
        release = (Button) findViewById(R.id.releaseButton);
        spinner = (Spinner) findViewById(R.id.statusSpin);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        requiredEquipment = (ListView)findViewById(R.id.equipmentList);
        requiredEquipment.setAdapter(listAdapter);
        updateView();


    }

    public void updateView(){
        personName.setText(t.getName());
        spinner.setSelection(t.getStatus());
        for (Item i : t.getEquiptment()){
            items.add(i.getName());
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_button:
                Bundle bundle = new Bundle();
                bundle.putParcelable("task", t);
                Intent intent = new Intent(TaskIndividualActivity.this, NewTaskActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        t.setStatus(position);
        util.saveTask(t);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
