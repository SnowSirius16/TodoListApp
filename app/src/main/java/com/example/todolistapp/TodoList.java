package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodoList extends AppCompatActivity {
    public String url = "https://mgm.ub.ac.id/todo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        TextView tvJudul = findViewById(R.id.tv_Judul);
        List<myList> dolist = new ArrayList<>();
        RecyclerView rView = findViewById(R.id.rvList);
        myList myList;
        Handler h = new Handler(Looper.getMainLooper());
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(TodoList.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject Obj = response.getJSONObject(i);
                                String id = Obj.getString("id");
                                String what = Obj.getString("what");
                                String time = Obj.getString("time");
                                dolist.add(new myList(id, what, time));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                ListAdapter adapter = new ListAdapter(TodoList.this, (ArrayList<myList>) dolist);
                                rView.setLayoutManager(new LinearLayoutManager(TodoList.this));
                                rView.setAdapter(adapter);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                queue.add(jsonArrayRequest);
            }
        });
        t.start();
    }
}