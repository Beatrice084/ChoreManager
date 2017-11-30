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
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.beajo.choremanager2.model.Item;
import com.example.beajo.choremanager2.R;

import java.util.ArrayList;

public class ShoppingAdapter extends ArrayAdapter<Item> {
    public ShoppingAdapter(@NonNull Context context, ArrayList<Item> people) {
        super(context, 0, people);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.shopping_item_layout, parent, false);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.bought);
        TextView name = (TextView) v.findViewById(R.id.itemName);
        Item p = getItem(position);
        name.setText(p.getName());
        return v;
    }
}
