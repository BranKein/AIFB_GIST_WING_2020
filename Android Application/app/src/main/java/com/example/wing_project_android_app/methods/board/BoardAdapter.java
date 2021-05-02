package com.example.wing_project_android_app.methods.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wing_project_android_app.R;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {

    ArrayList<Board> items = new ArrayList<Board>();
    Context context;

    public void addItem(Board item) {
        items.add(item);
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        context = parent.getContext();
        Board listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.board_view, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.board_text);
        textView.setText(listItem.getBoard_str());

        return convertView;
    }
}
