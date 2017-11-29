package com.example.beajo.choremanager2.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.beajo.choremanager2.MyDBHandler;
import com.example.beajo.choremanager2.Person;
import com.example.beajo.choremanager2.R;

/**
 * Created by oguns on 11/28/2017.
 */

public class NewPersonDialog extends DialogFragment {

    EditText nameView;
    RadioGroup group;
    RadioButton maleButton, femaleButton;
    Button save;
    NewPersonDialogListener callback;

    interface NewPersonDialogListener{
        void dataSaved();
        void saveStatus(int i);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_person_layout, container, false);

        nameView = (EditText)v.findViewById(R.id.personName);
        group = (RadioGroup)v.findViewById(R.id.group);
        maleButton = (RadioButton)v.findViewById(R.id.maleRadioButton);
        femaleButton = (RadioButton)v.findViewById(R.id.femaleRadioButton);
        save = (Button)v.findViewById(R.id.addPersonButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save()){
                    callback.dataSaved();
                }
            }
        });

        return v;
    }

    private boolean save(){
        MyDBHandler dbHandler = new MyDBHandler(getContext());
        Person p = verify();
        boolean stat = true;
        if(p != null){
            stat = dbHandler.addPerson(p);
            if(stat){
                callback.saveStatus(1);
            }
            else {
                callback.saveStatus(0);
            }
            return true;
        }
        return false;
    }

    private Person verify(){
        boolean state = true;
        String name = nameView.getText().toString();
        Person p = new Person();
        if(TextUtils.isEmpty(name)){
            nameView.setError("Name can't be empty");
            state = false;
        }
        else {
            p.setName(name);
        }

        if(maleButton.isChecked()){
            p.setImage(R.drawable.male);
        }else if(femaleButton.isChecked()){
            p.setImage(R.drawable.female);
        }else {
            Toast.makeText(getContext(), "Please select gender", Toast.LENGTH_SHORT).show();
            state = false;
        }
        if(state == true) return p;
        return null;
    }

    public static NewPersonDialog newInstance(){
        return new NewPersonDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (NewPersonDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
