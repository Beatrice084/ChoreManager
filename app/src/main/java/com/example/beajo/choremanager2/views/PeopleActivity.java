package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.beajo.choremanager2.Person;
import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.adapters.PersonAdapter;

import java.util.ArrayList;
import java.util.Random;

public class PeopleActivity extends AppCompatActivity {

    ArrayList<Person> p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView peopleList = (ListView)findViewById(R.id.list);
        p = getRandmonPeople();
        PersonAdapter adapter = new PersonAdapter(this, p);
        peopleList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), p.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void shoppingButtonClick(View view){//Starts Shopping activity
        Intent shoppingIntent = new Intent(getApplicationContext(), ShoppingActivity.class);
        startActivity(shoppingIntent);

    }

    public void otherButtonClick(View view){//Starts other activity
        Intent otherIntent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(otherIntent);
    }

    public void tasksButtonClick(View view){//Starts ChoreList activity
        Intent tasksIntent = new Intent(getApplicationContext(), ActivityChoreList.class);
        startActivity(tasksIntent);
    }

    private ArrayList<Person> getRandmonPeople(){
        ArrayList<Person> people = new ArrayList<>();
        Person a = new Person("Toluwani Ogunsanya", R.drawable.male);
        Person b = new Person("Alex Yuille", R.drawable.male);
        Person c = new Person("Saheed Akinbile", R.drawable.male);
        Person d = new Person("Beatrice Johnston", R.drawable.female);

        Random r = new Random();

        for (int i = 0; i < 10; i++){
            int j = r.nextInt(4)+1;
            if(j == 1){
                people.add(a);
            }
            else if(j == 2){
                people.add(b);
            }
            else if(j == 3){
                people.add(c);
            }
            else{
                people.add(d);
            }
        }
        return people;
    }

}
