package com.example.wing_project_android_app.methods.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wing_project_android_app.R;

import java.util.ArrayList;

public class ContentAdapter extends BaseAdapter {
    ArrayList<Content> items = new ArrayList<Content>();
    Context context;

    public void addItem(Content item) {
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
    public boolean isEnabled (int position){
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        context = parent.getContext();
        Content listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_view, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.contents_img);
        TextView textView = convertView.findViewById(R.id.contents_text);
        imageView.setClickable(false);
        textView.setClickable(false);

        if(listItem.getIfimg()){
            textView.setVisibility(View.GONE);
            imageView.setImageBitmap(listItem.getSrc());
        }
        else{
            textView.setText(listItem.getContent_str());
            imageView.setVisibility(View.GONE);
        }

        return convertView;
    }
}
