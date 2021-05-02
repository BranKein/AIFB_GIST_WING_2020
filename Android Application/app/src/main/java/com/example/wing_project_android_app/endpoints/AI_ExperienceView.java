package com.example.wing_project_android_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wing_project_android_app.R;
import com.example.wing_project_android_app.methods.ai.AI_Experience;
import com.example.wing_project_android_app.methods.ai.AI_ExperienceAdapter;

public class AI_ExperienceView extends AppCompatActivity {
    ListView ai_experience_listview;
    AI_ExperienceAdapter ai_experience_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_experience_page);

        ai_experience_listview = (ListView) findViewById(R.id.ai_experience_listview);
        ai_experience_adapter = new AI_ExperienceAdapter();

        ai_experience_adapter.addItem(new AI_Experience("Digit Classifier", DigitClassifierView.class));

        ai_experience_listview.setAdapter(ai_experience_adapter);

        ai_experience_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AI_Experience clicked_experience = (AI_Experience) ai_experience_adapter.getItem(position);
                Intent intent = new Intent(AI_ExperienceView.this, clicked_experience.getExperience_class());

                startActivity(intent);
            }
        });

    }
}