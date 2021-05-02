package com.example.wing_project_android_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wing_project_android_app.R;
import com.example.wing_project_android_app.methods.board.Board;
import com.example.wing_project_android_app.methods.board.BoardAdapter;
import com.example.wing_project_android_app.methods.board.Board_manager;

import java.util.ArrayList;

public class BoardView extends AppCompatActivity {

    ListView board_listview;
    BoardAdapter board_adapter;

    ArrayList<String> board_name_list;
    Board_manager board_manager;

    Request_Thread request_thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_page);

        board_manager = new Board_manager();
        request_thread = new Request_Thread();
        request_thread.start();

        try{
            request_thread.join();
        } catch (InterruptedException e) {

        }

        board_listview = (ListView) findViewById(R.id.board_listview);
        board_adapter = new BoardAdapter();

        for (int i = 0; i < board_name_list.size(); i++) {
            board_adapter.addItem(new Board(board_name_list.get(i)));
        }
        board_adapter.addItem(new Board("외전: 인공지능 체험해보기"));
        board_listview.setAdapter(board_adapter);

        board_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Board clicked_board = (Board) board_adapter.getItem(position);
                Intent intent;
                if (clicked_board.getBoard_str().equals("외전: 인공지능 체험해보기")) {
                    intent = new Intent(BoardView.this, AI_ExperienceView.class);

                } else {
                    intent = new Intent(BoardView.this, ContentView.class);
                    intent.putExtra("boardname", clicked_board.getBoard_str());
                }

                startActivity(intent);
            }
        });

    }

    private class Request_Thread extends Thread {
        public void run() {
            board_name_list = board_manager.get_board_list();
        }
    }
}
