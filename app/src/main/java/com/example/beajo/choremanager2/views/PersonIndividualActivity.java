package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.adapters.TaskAdapter;
import com.example.beajo.choremanager2.model.AppContract;
import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;

import java.util.ArrayList;

public class PersonIndividualActivity extends AppCompatActivity implements Utils.DataChangeListener {
    private ListView currentTasks;
    TaskAdapter taskAdapter;
    Utils util;
    TextView nameView, pointsView;
    Person p;
    private static final String TAG = PersonIndividualActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_individual);

        Bundle bundle = getIntent().getExtras();
        p = bundle.getParcelable(AppContract.PERSON_BUNDLE);

        nameView =(TextView)findViewById(R.id.personName);
        pointsView =(TextView)findViewById(R.id.personsPoints);
        currentTasks = (ListView)findViewById(R.id.currentList);

        util = new Utils();
        util.initListener(this);

        nameView.setText(p.getName());
        pointsView.setText(Integer.toString(p.getPoints()));

        taskAdapter = new TaskAdapter(this, util.getTasks(p.getUid()));
        util.registerAdapter(taskAdapter.getKey(), taskAdapter);
        currentTasks.setAdapter(taskAdapter);

        currentTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startTaskIndividual(util.getTasks().get(position));
            }
        });

        ArrayList<TaskItem> tasks = Utils.getTasks(p.getUid());
        TaskAdapter adp = new TaskAdapter(this,tasks);
        currentTasks.setAdapter(adp);

    }

    public void startTaskIndividual(TaskItem taskItem){
        Bundle bundle = new Bundle();
        bundle.putParcelable("task", taskItem);
        Intent intent = new Intent(PersonIndividualActivity.this, TaskIndividualActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void updateView(){
        nameView.setText(p.getName());
        pointsView.setText(Integer.toString(p.getPoints()));
    }

    @Override
    public void personChanged(Person p) {
        Log.d(TAG, "1.) " + this.p.getName() + " " + this.p.getPoints());
        this.p = p;
        Log.d(TAG, "2.) " + this.p.getName() + " " + this.p.getPoints());
        updateView();
    }
}
