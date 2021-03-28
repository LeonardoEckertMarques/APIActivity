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
import com.example.apiactivity.model.Comments;
import com.example.apiactivity.model.Posts;
import com.example.apiactivity.model.Todos;

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
  List<Todos> todos = new ArrayList<>();
  List<Comments> comments = new ArrayList<>();

  private TextView tipo;
  private String op;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista);
    tipo = findViewById(R.id.listaSelecionada);
    op = getIntent().getStringExtra("op");

    switch (op) {
      case "posts":
      case "albums":
      case "todos":
      case "comments":
        tipo.setText("Lista Selecionada: \n" +op.toUpperCase());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/" + op;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);
        break;
      default:
        break;
    }

  }

  @Override
  public void onResponse(JSONArray response) {

    try {

      LinearLayout layout = findViewById(R.id.layoutVerticalItens);

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

          Toast.makeText(this,"Recebido: " + posts.size() + " posts",Toast.LENGTH_LONG).show();

          for(Posts obj1 : posts) {
            Button bt = new Button(this);
            bt.setText(obj1.getTitle());
            bt.setTag(obj1);
            bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Button btn = (Button) v;
                Posts tp = (Posts) btn.getTag();
                Intent i = new Intent(ListsActivity.this, DetalhesActivity.class);
                i.putExtra("op", op);
                i.putExtra("objTp", tp);
                startActivity(i);
              }
            });
            layout.addView(bt);
          }
        break;
        case "albums":

          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Albums obj = new Albums(
                    json.getInt("userId"),
                    json.getInt("id"),
                    json.getString("title"));
            albums.add(obj);
          }

          Toast.makeText(this,"Recebido: " + albums.size() + " albums",Toast.LENGTH_LONG).show();

          for(Albums albums_obj : albums) {
            Button bt = new Button(this);
            bt.setText(albums_obj.getTitle());
            bt.setTag(albums_obj);
            bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Button btn = (Button) v;
                Albums tp = (Albums) btn.getTag();
                Intent i = new Intent(ListsActivity.this, DetalhesActivity.class);
                i.putExtra("op", op);
                i.putExtra("objTp", tp);
                startActivity(i);
              }
            });
            layout.addView(bt);
          }
          break;
        case "todos":
          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Todos obj = new Todos(
                    json.getInt("userId"),
                    json.getInt("id"),
                    json.getString("title"),
                    json.getBoolean("completed"));
            todos.add(obj);
          }
          Toast.makeText(this,"Recebido : " + todos.size() + " todos",Toast.LENGTH_LONG).show();
          for(Todos todos_obj : todos) {
            Button bt = new Button(this);
            bt.setText(todos_obj.getTitle());
            bt.setTag(todos_obj);
            bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Button btn = (Button) v;
                Todos tp = (Todos) btn.getTag();
                Intent i = new Intent(ListsActivity.this, DetalhesActivity.class);
                i.putExtra("op", op);
                i.putExtra("objTp", tp);
                startActivity(i);
              }
            });
            layout.addView(bt);
          }
          break;
        case "comments":
          for(int i = 0; i < response.length(); i++) {
            JSONObject json = response.getJSONObject(i);
            Comments obj = new Comments(
                    json.getInt("postId"),
                    json.getInt("id"),
                    json.getString("name"),
                    json.getString("email"),
                    json.getString("body"));
            comments.add(obj);
          }
          Toast.makeText(this,"Recebido: " + comments.size() + " comments",Toast.LENGTH_LONG).show();
          for(Comments comments_obj : comments) {
            Button bt = new Button(this);
            bt.setText(comments_obj.getName());
            bt.setTag(comments_obj);
            bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                Button btn = (Button) v;
                Comments tp = (Comments) btn.getTag();
                Intent i = new Intent(ListsActivity.this, DetalhesActivity.class);
                i.putExtra("op", op);
                i.putExtra("objTp", tp);
                startActivity(i);
              }
            });
            layout.addView(bt);
          }
          break;
      }// fim do switch

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