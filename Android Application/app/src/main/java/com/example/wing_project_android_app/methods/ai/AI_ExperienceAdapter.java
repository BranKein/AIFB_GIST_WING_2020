package com.example.wing_project_android_app.methods.ai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wing_project_android_app.R;

import java.util.ArrayList;

public class AI_ExperienceAdapter extends BaseAdapter {
    ArrayList<AI_Experience> items = new ArrayList<AI_Experience>();
    Context context;

    public void addItem(AI_Experience item) {
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
        AI_Experience listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ai_experience_view, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.ai_experience_text);
        textView.setText(listItem.getExperience_str());

        return convertView;
    }
}
