package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apiactivity.model.Albums;
import com.example.apiactivity.model.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener {

  List<Posts> posts =  new ArrayList<>();
  List<Albums> albums =  new ArrayList<>();

  private TextView tv;
  private String op;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista);

    TextView tv = findViewById(R.id.textoSegunda);

    op = getIntent().getStringExtra("op");

    switch (op) {
      case "posts":
        tv.setText(op);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);

        queue.add(jsonArrayRequest);
        break;
      case "albums":
        tv.setText(op);
        RequestQueue queue1 = Volley.newRequestQueue(this);
        String url1 = "https://jsonplaceholder.typicode.com/albums";

        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Request.Method.GET, url1, null,
                this, this);

        queue1.add(jsonArrayRequest1);
      default:
        break;
    }

  }

  @Override
  public void onResponse(JSONArray response) {

    try {

      switch (op) {
        case "posts":

          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Posts obj = new Posts(
                    json.getInt("userId"),
                    json.getInt("id"),
                    json.getString("title"),
                    json.getString("body"));
            posts.add(obj);
          }

          LinearLayout ll = findViewById(R.id.layoutVerticalItens);
          Toast.makeText(this,"qtd:"+posts.size(),Toast.LENGTH_LONG).show();

          for(Posts obj1 : posts) {
            Button bt = new Button(this);
            bt.setText(obj1.getTitle());
            bt.setTag(obj1);
            bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Button btn = (Button) v;
                Posts todo = (Posts) btn.getTag();
                Intent intent = new Intent(ListsActivity.this, DetalhesActivity.class);
                intent.putExtra("objTodo", todo);
                startActivity(intent);
              }
            });
            ll.addView(bt);
          }

        break;
        case "albums":

          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Albums obj2 = new Albums(
                    json.getInt("userId"),
                    json.getInt("id"),
                    json.getString("title"));
            albums.add(obj2);
          }

          LinearLayout ll1 = findViewById(R.id.layoutVerticalItens);
          Toast.makeText(this,"qtd:"+albums.size(),Toast.LENGTH_LONG).show();

          for(Albums obj2 : albums) {
            Button bt = new Button(this);
            bt.setText(obj2.getTitle());
            bt.setTag(obj2);
            bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Button btn = (Button) v;
                Albums todo = (Albums) btn.getTag();
                Intent intent = new Intent(ListsActivity.this, DetalhesActivity.class);
                intent.putExtra("objTodo", todo);
                startActivity(intent);
              }
            });
            ll1.addView(bt);
          }

          break;
      }

      } catch (JSONException e) {
        Log.e("JSONException",e.getMessage());
        e.printStackTrace();
    }

  }

  @Override
  public void onErrorResponse(VolleyError error) {
    String msg = error.getMessage();
    Toast.makeText(ListsActivity.this,"onErrorResponse: "+msg,Toast.LENGTH_LONG).show();
  }

}