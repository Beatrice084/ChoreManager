package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.adapters.TaskAdapter;
import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;

import java.util.ArrayList;

public class PersonIndividualActivity extends AppCompatActivity {
    private ListView currentTasks;
    TaskAdapter taskAdapter;
    Utils util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_individual);
        Bundle bundle = getIntent().getExtras();
        Person p = bundle.getParcelable("person");

        TextView t=(TextView)findViewById(R.id.personName2);

        t.setText(p.getName());

        TextView t1=(TextView)findViewById(R.id.personsPoints);

        t1.setText(Integer.toString(p.getPoints()));

        currentTasks = (ListView)findViewById(R.id.currentList);
        util = new Utils();

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
}
