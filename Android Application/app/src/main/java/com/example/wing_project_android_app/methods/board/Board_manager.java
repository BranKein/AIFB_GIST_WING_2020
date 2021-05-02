package com.example.wing_project_android_app.methods.board;

import android.util.Log;

import com.example.wing_project_android_app.methods.Query_manager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Board_manager extends Thread{
    Query_manager query_manager;
    ArrayList<String> result_arrlist;
    String request_url = "http://52.79.54.196:5000/board/get_board_list";


    public Board_manager() {
        query_manager = new Query_manager();
        result_arrlist = new ArrayList<String>();
    }

    public ArrayList<String> get_board_list() {
        try {
            JSONArray result = query_manager.reqeust_arr(request_url);
            for (int i = 0; i < result.length(); i++) {
                result_arrlist.add(result.getJSONObject(i).getString("description"));
            }
        }
        catch (IOException e) {
            result_arrlist.add("IOException Occurred!");
            return result_arrlist;
        }
        catch (JSONException e) {
            result_arrlist.add("JSONException Occurred!");
            return result_arrlist;
        }

        return result_arrlist;
    }
}
