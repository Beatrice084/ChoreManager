package com.example.beajo.choremanager2.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.adapters.TaskAdapter;
import com.example.beajo.choremanager2.model.Person;
import com.example.beajo.choremanager2.model.TaskItem;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityChoreList extends AppCompatActivity {
    private final String TAG = ActivityChoreList.class.getSimpleName();
    private static final int RC_SIGN_IN = 123;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Utils util;
    TaskAdapter adapter;
    ListView taskList;
    ArrayList<TaskItem> t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);
        Log.d(TAG, "home");
        util = new Utils();
        util.downloadPeople();
        util.downloadTasks();

        taskList = (ListView)findViewById(R.id.taskList);
        t = util.getTasks();

        adapter = new TaskAdapter(this, t);
        taskList.setAdapter(adapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Item clicked");
                Toast.makeText(view.getContext(), t.get(position).getName(), Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                b.putParcelable("task", t.get(position));
                Intent taskIntent = new Intent(getApplicationContext(), TaskIndividualActivity.class);
                taskIntent.putExtras(b);
                startActivity(taskIntent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            signIn();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == ResultCodes.OK) {
                // Successfully signed in
                mUser = mAuth.getCurrentUser();
                uploadUser();
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    public void signIn(){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    public void shoppingButtonClick(View view){//Starts Shopping activity
        Intent shoppingIntent = new Intent(getApplicationContext(), ShoppingActivity.class);
        startActivity(shoppingIntent);
        finish();

    }

    public void peopleButtonClick(View view){//Starts people_list activity
        Intent peopleIntent = new Intent(getApplicationContext(), PeopleActivity.class);
        startActivity(peopleIntent);
        finish();
    }

    public void otherButtonClick(View view){//Starts other activity
        Intent otherIntent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(otherIntent);
        finish();
    }

    public void newTaskButtonClick(View view){
        Intent otherIntent = new Intent(getApplicationContext(), NewTaskActivity.class);
        startActivity(otherIntent);
    }


    public void uploadUser(){
        Person p = new Person();
        FirebaseUser user = mAuth.getCurrentUser();
        p.setName(user.getDisplayName());
        p.setEmail(user.getEmail());
        p.setGender(R.drawable.male);
        p.setUid(user.getUid());
        util.addUser(p);
    }
    

}
