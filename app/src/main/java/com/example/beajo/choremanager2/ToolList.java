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

import java.util.List;

public class ToolList extends ArrayAdapter<Tool>{
    private Activity context;
    List<Tool> tools;

    public ToolList(Activity context, List<Tool> tools) {
        super(context, R.layout.layout_tool_list, tools);
        this.context = context;
        this.tools = tools;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_tool_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        Tool tool = tools.get(position);
        textViewName.setText(tool.getToolName());
        return listViewItem;
    }
}
