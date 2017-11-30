package com.example.beajo.choremanager2.views;

/**
 * Created by beajo on 2017-11-29.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beajo.choremanager2.R;

import java.util.List;

public class CupboardList {
    private Activity context;
    List<Cupboard> cupboards;

    public CupboardList(Activity context, List<Cupboard> cupboards) {
        super(context, R.layout.layout_cupboard_list, cupboards);
        this.context = context;
        this.cupboards = cupboards;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_tool_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        Cupboard cupboard = cupboards.get(position);
        textViewName.setText(cupboard.getCupboardName());
        return listViewItem;
    }
}
