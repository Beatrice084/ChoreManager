package com.example.beajo.choremanager2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.model.TaskItem;

public class TaskIndividualActivity extends AppCompatActivity {

    private ListView requiredEquipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_individual);

        Bundle bundle = getIntent().getExtras();
        TaskItem t = bundle.getParcelable("task");

        EditText n = new EditText(this);
        n=(EditText) findViewById(R.id.editNote);
        n.setText(t.getNote());

        Spinner s = new Spinner(this);
        s= (Spinner) findViewById(R.id.statusSpin);
        s.setSelection(t.getStatus());

       requiredEquipment = (ListView)findViewById(R.id.equipmentList);


    }
}
