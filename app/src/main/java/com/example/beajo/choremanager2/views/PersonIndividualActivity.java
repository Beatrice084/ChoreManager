package com.example.beajo.choremanager2.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.model.Person;

public class PersonIndividualActivity extends AppCompatActivity {
    private ListView currentTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_individual);
        Bundle bundle = getIntent().getExtras();
        Person p = bundle.getParcelable("person");

        TextView t = new TextView(this);
        t=(TextView)findViewById(R.id.personName2);

        t.setText(p.getName());

        TextView t1 = new TextView(this);
        t1=(TextView)findViewById(R.id.personsPoints);

        t1.setText(Integer.toString(p.getPoints()));

        currentTasks = (ListView)findViewById(R.id.currentList);








    }
}
