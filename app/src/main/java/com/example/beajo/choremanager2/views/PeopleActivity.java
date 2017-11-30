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
import com.example.beajo.choremanager2.model.Person;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.adapters.PersonAdapter;

import java.util.ArrayList;
import java.util.Random;

public class PeopleActivity extends AppCompatActivity {

    ArrayList<Person> p;
    //NewPersonDialog dialog;
    FragmentTransaction ft;
    PersonAdapter adapter;
    private final String TAG = PeopleActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView peopleList = (ListView)findViewById(R.id.list);
        //p = getRandmonPeople();
        p = new ArrayList<>();
        PersonAdapter adapter = new PersonAdapter(this, p);

        peopleList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "selected");
                Toast.makeText(view.getContext(), p.get(position).getName(), Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                b.putString("name", p.get(position).getName());
                Intent personIntent = new Intent(getApplicationContext(), PersonIndividualActivity.class);
                personIntent.putExtras(b);
                startActivity(personIntent);
            }
        });
    }

    public void shoppingButtonClick(View view){//Starts Shopping activity
        Intent shoppingIntent = new Intent(getApplicationContext(), ShoppingActivity.class);
        startActivity(shoppingIntent);
        finish();
    }

    public void otherButtonClick(View view){//Starts other activity
        Intent otherIntent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(otherIntent);
        finish();
    }

    public void tasksButtonClick(View view){//Starts ChoreList activity
        Intent tasksIntent = new Intent(getApplicationContext(), ActivityChoreList.class);
        startActivity(tasksIntent);
        finish();
    }

//    private ArrayList<Person> getRandmonPeople(){
//        ArrayList<Person> people = new ArrayList<>();
//        Person a = new Person("Toluwani Ogunsanya", R.drawable.male);
//        Person b = new Person("Alex Yuille", R.drawable.male);
//        Person c = new Person("Saheed Akinbile", R.drawable.male);
//        Person d = new Person("Beatrice Johnston", R.drawable.female);
//
//        Random r = new Random();
//
//        for (int i = 0; i < 10; i++){
//            int j = r.nextInt(4)+1;
//            if(j == 1){
//                people.add(a);
//            }
//            else if(j == 2){
//                people.add(b);
//            }
//            else if(j == 3){
//                people.add(c);
//            }
//            else{
//                people.add(d);
//            }
//        }
//        return people;
//    }

//    private void showDialog(){
//        ft = getSupportFragmentManager().beginTransaction();
//        dialog = NewPersonDialog.newInstance();
//        dialog.show(ft, null);
//    }
//
//    @Override
//    public void dataSaved() {
//        dialog.dismiss();
//    }
//
//    @Override
//    public void saveStatus(int i) {
//        if(i == 0){
//            Toast.makeText(this, "Save failed", Toast.LENGTH_LONG).show();
//        }
//        else {
//            Toast.makeText(this, "Save successful", Toast.LENGTH_LONG).show();
//        }
//    }

}
