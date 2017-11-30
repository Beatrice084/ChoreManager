package com.example.beajo.choremanager2.views;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beajo.choremanager2.Tool;
import com.example.beajo.choremanager2.ToolList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.beajo.choremanager2.R;

public class CupboardFridgeActivity extends AppCompatActivity {
    EditText editCupboardName;
    Button buttonAddCupboard;
    ListView listViewCupbaords;

    List<Cupboard> cupboards;
    DatabaseReference databaseCupboards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupboard_fridge);

        databaseCupboards = FirebaseDatabase.getInstance().getReference("cupboards");
        editCupboardName = (EditText) findViewById(R.id.editCupboardName);
        listViewCupbaords = (ListView) findViewById(R.id.listViewCupboards);
        buttonAddCupboard = (Button) findViewById(R.id.addButton);

       cupboards = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddCupboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCupboard();
            }
        });

        listViewCupbaords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cupboard cupboard = cupboards.get(i);
                showUpdateDeleteDialog(cupboard.getId(), cupboard.getCupboardName());
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseCupboards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                cupboards.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting cupboard
                    Cupboard cupboard = postSnapshot.getValue(Cupboard.class);
                    //adding cupboard to the list
                    cupboards.add(cupboard);
                }

                //creating adapter
                CupboardList cupboardsAdapter = new CupboardList(CupboardFridgeActivity.this, cupboards);
                //attaching adapter to the listview
                listViewCupbaords.setAdapter(cupboardsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void showUpdateDeleteDialog(final String cupboardId, String cupboardName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cupboard_fridge_popup, null);
        dialogBuilder.setView(dialogView);

        final EditText editCupboardName = (EditText) dialogView.findViewById(R.id.editCupboardName);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateCupbaord);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteCupbaord);

        dialogBuilder.setTitle(cupboardName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editCupboardName.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateCupboard(cupboardId, name);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCupboard(cupboardId);
                b.dismiss();
            }
        });
    }

    private void updateCupboard(String id, String name) {
        //getting the specified cupboard reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cupboards").child(id);
        //updating cupboard
        Cupboard cupboard = new Cupboard(id, name);
        dR.setValue(cupboard);
        Toast.makeText(getApplicationContext(), "Cupboard or Fridge Item Updated", Toast.LENGTH_LONG).show();

    }

    private boolean deleteCupboard(String id) {
        //getting the specified cupboard reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cupboards").child(id);
        //removing prodct
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Cupboard Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addCupboard() {
        //getting the values to save
        String name = editCupboardName.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our cupboard
            String id = databaseCupboards.push().getKey();

            //creating an cupboard Object
            Cupboard cupboard = new Cupboard(id, name);

            //Saving the Cupboard
            databaseCupboards.child(id).setValue(cupboard);

            //setting edittext to blank again
            editCupboardName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Cupboard or Fridge Item Added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
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

    public void tasksButtonClick(View view){//Starts ChoreList activity
        Intent tasksIntent = new Intent(getApplicationContext(), ActivityChoreList.class);
        startActivity(tasksIntent);
        finish();
    }

    public void otherButtonClick(View view){//Starts other activity
        Intent otherIntent = new Intent(getApplicationContext(), OtherActivity.class);
        startActivity(otherIntent);
        finish();
    }
}
