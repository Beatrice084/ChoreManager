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
import com.example.beajo.choremanager2.R;

/**
 * Created by oguns on 11/28/2017.
 */

public class NewPersonDialog extends DialogFragment {

    EditText nameView;
    RadioGroup group;
    RadioButton maleButton, femaleButton;
    Button save;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_person_layout, container, false);

        nameView = (EditText)v.findViewById(R.id.personName);
        group = (RadioGroup)v.findViewById(R.id.group);
        maleButton = (RadioButton)v.findViewById(R.id.maleRadioButton);
        femaleButton = (RadioButton)v.findViewById(R.id.femaleRadioButton);
        save = (Button)v.findViewById(R.id.addPersonButton);
        return v;
    }

   

}
