package com.example.beajo.choremanager2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beajo.choremanager2.Person;
import com.example.beajo.choremanager2.R;

import java.util.ArrayList;

/**
 * Created by oguns on 11/27/2017.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(@NonNull Context context, ArrayList<Person> people) {
        super(context, 0, people);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.person_item_layout, parent, false);
        ImageView image = (ImageView) v.findViewById(R.id.personImage);
        TextView name = (TextView) v.findViewById(R.id.personName);
        TextView allocatedTasks = (TextView) v.findViewById(R.id.personAllocatedTask);
        TextView nextTask = (TextView) v.findViewById(R.id.personNextTask);
        Person p = getItem(position);
        image.setImageResource(p.getImage());
        name.setText(p.getName());
        return v;
    }
}
