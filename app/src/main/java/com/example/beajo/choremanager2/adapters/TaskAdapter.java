package com.example.beajo.choremanager2.adapters;

/**
 * Created by saheed on 2017-11-29.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beajo.choremanager2.model.TaskItem;
import com.example.beajo.choremanager2.R;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<TaskItem> {
    public TaskAdapter(@NonNull Context context, ArrayList<TaskItem> tasks) {
        super(context, 0, tasks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.chore_item_layout, parent, false);
        ImageView image = (ImageView) v.findViewById(R.id.icon);
        TextView name = (TextView) v.findViewById(R.id.itemName);
        TextView description = (TextView) v.findViewById(R.id.itemDescription);
        TaskItem p = getItem(position);
        //image.setImageResource(p.getImage());
        name.setText(p.getName());
        description.setText(p.getNote());
        return v;
    }
}
