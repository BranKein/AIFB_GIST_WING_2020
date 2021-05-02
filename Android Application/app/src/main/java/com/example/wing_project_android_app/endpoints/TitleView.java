package com.example.wing_project_android_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wing_project_android_app.R;

public class TitleView extends AppCompatActivity {

    Button title_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);

        title_btn = (Button) findViewById(R.id.title_button);

        title_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TitleView.this, BoardView.class);
                startActivity(intent);
            }
        });
    }
}
