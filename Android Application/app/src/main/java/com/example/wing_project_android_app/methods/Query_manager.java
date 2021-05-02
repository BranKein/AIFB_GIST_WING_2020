package com.example.wing_project_android_app.methods;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Query_manager {
    private final OkHttpClient client;

    private String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public Query_manager() {
        client = new OkHttpClient();
    }

    public JSONArray reqeust_arr(String url) throws IOException, JSONException {
        String response = run(url);
        return new JSONArray(response);
    }
}
