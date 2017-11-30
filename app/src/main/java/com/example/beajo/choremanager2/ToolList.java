package com.example.beajo.choremanager2;

/**
 * Created by beajo on 2017-11-27.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beajo.choremanager2.model.Item;

import java.util.List;

public class ToolList extends ArrayAdapter<Item>{
    private Activity context;
    List<Item> tools;
    public static final String key = ToolList.class.getSimpleName();

    public ToolList(Activity context, List<Item> tools) {
        super(context, R.layout.layout_tool_list, tools);
        this.context = context;
        this.tools = tools;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_tool_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        Item tool = tools.get(position);
        textViewName.setText(tool.getName());
        return listViewItem;
    }

    public static String getKey() {
        return key;
    }
}
