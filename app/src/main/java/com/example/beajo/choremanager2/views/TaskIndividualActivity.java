package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.beajo.choremanager2.model.AppContract;
import com.example.beajo.choremanager2.model.Item;
import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TaskIndividualActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PeopleDialog.PeopleDialogListener {

    private ListView requiredEquipment;
    private ImageView personIcon;
    TextView personName, notesView, titleView;
    Button release;
    Spinner spinner;
    ArrayList<String> items;
    ArrayAdapter<CharSequence> spinnerAdapter;
    ArrayAdapter listAdapter;
    TaskItem t;
    Utils util;
    private static final String TAG = PersonIndividualActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_individual);
        util = new Utils();
        Bundle bundle = getIntent().getExtras();

        items = new ArrayList<>();
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.status, R.layout.item_layout);
        listAdapter = new ArrayAdapter(this, R.layout.item_layout, items);

        personIcon = (ImageView)findViewById(R.id.personIcon);
        personName = (TextView) findViewById(R.id.personName);
        notesView = (TextView) findViewById(R.id.noteView);
        titleView = (TextView) findViewById(R.id.title);
        release = (Button) findViewById(R.id.releaseButton);
        spinner = (Spinner) findViewById(R.id.statusSpin);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        requiredEquipment = (ListView)findViewById(R.id.equipmentList);
        requiredEquipment.setAdapter(listAdapter);

        personName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        t = bundle.getParcelable(AppContract.TASK_BUNDLE);
        if(t != null){
            Log.d(TAG, t.toString());
            updateView();
        }


    }

    public void updateView(){
        int index = util.binarySearchPerson(t.getUid());
        Person p;

        if(index > -1){
            p = util.getPeople().get(index);
            personName.setText(p.getName());
            //personIcon.setImageResource(p.getGender());
        }
        else {
            personName.setText(getString(R.string.not_assigned));
        }

        titleView.setText(t.getName());
        notesView.setText(t.getNote());
        spinner.setSelection(t.getStatus());
        items.clear();

        if(t.getEquipment() != null) {
            for (Item i : t.getEquipment()) {
                items.add(i.getName());
            }
        }

        listAdapter.notifyDataSetChanged();
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
            case R.id.delete_button:
                util.deleteTask(t);
                finish();
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

    public void realeaseClick(View v){
        t.setUid(null);
        util.saveTask(t);
        updateView();
    }

    @Override
    public void personClicked(Person p) {
        t.setUid(p.getUid());
        util.saveTask(t);
        updateView();
    }

    public void openDialog(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        PeopleDialog peopleDialog = new PeopleDialog();
        peopleDialog.show(ft, null);
    }
}
