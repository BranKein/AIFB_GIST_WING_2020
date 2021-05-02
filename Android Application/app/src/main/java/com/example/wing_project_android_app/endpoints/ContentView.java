package com.example.wing_project_android_app.endpoints;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.wing_project_android_app.R;
import com.example.wing_project_android_app.methods.content.Content;
import com.example.wing_project_android_app.methods.content.ContentAdapter;
import com.example.wing_project_android_app.methods.content.Content_manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ContentView extends AppCompatActivity {

    String board_name;

    TextView content_title;

    ListView content_listview;
    ContentAdapter content_adapter;

    ArrayList<String> content_string_list;
    Content_manager content_manager;
    AssetManager am;

    Request_Thread request_thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_page);

        Intent intent = getIntent();
        board_name = intent.getStringExtra("boardname");

        am = getResources().getAssets();

        content_manager = new Content_manager();
        request_thread = new Request_Thread();
        request_thread.start();

        try{
            request_thread.join();
        } catch (InterruptedException e) {

        }

        content_title = (TextView) findViewById(R.id.content_title);
        content_title.setText(board_name);

        content_listview = (ListView) findViewById(R.id.content_listview);
        content_adapter = new ContentAdapter();

        for (int i = 0; i < content_string_list.size(); i++) {
            InputStream is = null;
            String content = content_string_list.get(i);
            if (content.length() >= 9 && content.substring(0, 7).equals("[<image")) {
                try {
                    is = am.open(content.substring(2, content.length()-2) + ".png");
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    content_adapter.addItem(new Content(content, bm, true));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    is = am.open("back.png");
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    content_adapter.addItem(new Content(content, bm, false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        content_listview.setAdapter(content_adapter);
    }

    private class Request_Thread extends Thread {
        public void run() {
            content_string_list = content_manager.get_content_list(board_name);
        }
    }
}
