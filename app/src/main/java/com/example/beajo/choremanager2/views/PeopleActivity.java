package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.beajo.choremanager2.MyDBHandler;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.model.AppContract;
import com.example.beajo.choremanager2.model.Person;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.adapters.PersonAdapter;

import java.util.ArrayList;
import java.util.Random;

public class PeopleActivity extends AppCompatActivity {

    ArrayList<Person> p;
    Utils util;
    //NewPersonDialog dialog;
    FragmentTransaction ft;
    PersonAdapter adapter;
    ListView peopleList;
    private final String TAG = PeopleActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        util = new Utils();
        peopleList = (ListView)findViewById(R.id.list);
        p = new ArrayList<>();
        adapter = new PersonAdapter(this, p);

        peopleList.setAdapter(adapter);


        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Item clicked");
                Toast.makeText(view.getContext(), p.get(position).getName(), Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                b.putParcelable(AppContract.PERSON_BUNDLE, p.get(position));
                Intent personIntent = new Intent(getApplicationContext(), PersonIndividualActivity.class);
                personIntent.putExtras(b);
                startActivity(personIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        p.clear();
        p.addAll(util.getPeople());
        adapter.notifyDataSetChanged();
    }

    public void shoppingButtonClick(View view){//Starts Shopping activity
        Intent shoppingIntent = new Intent(getApplicationContext(), ShoppingActivity.class);
        startActivity(shoppingIntent);
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
}
