package com.elena.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Tienda>{

    public CustomAdapter(Context context, int textViewResourceId,
                         Tienda [] objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row, null);
        TextView list  = (TextView)convertView.findViewById(R.id.textViewList);
        Tienda c = getItem(position);
        list.setText(c.toStringman());
        return convertView;
    }

}
