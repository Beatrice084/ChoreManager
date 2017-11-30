package com.example.beajo.choremanager2.adapters;

/**
 * Created by saheed on 2017-11-29.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beajo.choremanager2.Utils;
import com.example.beajo.choremanager2.model.TaskItem;
import com.example.beajo.choremanager2.R;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<TaskItem> {
    static final String key = TaskAdapter.class.getSimpleName();
    private final String TAG = TaskAdapter.class.getSimpleName();
    Utils utils;
    public TaskAdapter(@NonNull Context context, ArrayList<TaskItem> tasks) {
        super(context, 0, tasks);
        utils = new Utils();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.chore_item_layout, parent, false);
        ImageView image = (ImageView) v.findViewById(R.id.icon);
        TextView name = (TextView) v.findViewById(R.id.itemName);
        TextView description = (TextView) v.findViewById(R.id.itemDescription);
        CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkBox);
        final TaskItem p = getItem(position);
        //image.setImageResource(p.getImage());
        name.setText(p.getName());
        description.setText(p.getNote());
        if(p.getStatus() == 1){
            checkBox.setChecked(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "Points change");
                if(isChecked){
                    p.setStatus(1);
                    utils.updateScore(p.getUid(), 1);
                }else {
                    p.setStatus(0);
                    utils.updateScore(p.getUid(), 0);
                }
                utils.saveTask(p);
            }
        });
        return v;
    }

    public static String getKey(){
        return key;
    }
}
