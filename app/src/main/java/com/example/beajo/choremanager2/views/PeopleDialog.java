package com.example.beajo.choremanager2.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beajo.choremanager2.R;
import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.model.Person;

import java.util.ArrayList;

/**
 * Created by oguns on 11/30/2017.
 */

public class PeopleDialog extends DialogFragment {

    ArrayAdapter<String> adapter;
    Utils utils;
    PeopleDialogListener mCallback;
    interface PeopleDialogListener{
        void personClicked(Person p);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.people_fragment_layout, container, false);
        // Watch for button clicks.
        utils = new Utils();
        ListView listView = (ListView)v.findViewById(R.id.list);
        adapter = new ArrayAdapter(v.getContext(), R.layout.item_layout, getPeople());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.personClicked(utils.getPeople().get(position));
                dismiss();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (PeopleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public ArrayList<String> getPeople(){
        ArrayList<String> people = new ArrayList<>();
        for (Person p : utils.getPeople()){
            people.add(p.getName());
        }
        return people;
    }
}
