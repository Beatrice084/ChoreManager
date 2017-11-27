package com.example.beajo.choremanager2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
    }

    public void peopleButtonClick(View view){//Starts people_list activity
        Intent peopleIntent = new Intent(getApplicationContext(), activity_people_list.class);
        startActivity(peopleIntent);

    }

    public void otherButtonClick(View view){//Starts other activity
        Intent otherIntent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(otherIntent);
    }

    public void tasksButtonClick(View view){//Starts ChoreList activity
        Intent tasksIntent = new Intent(getApplicationContext(), ActivityChoreList.class);
        startActivity(tasksIntent);
    }

}
