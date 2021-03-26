package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListsBotao extends AppCompatActivity {

  private Button posts;
  private Button albums;
  private Button todos;
  private Button comments;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_botoes);

    posts = findViewById(R.id.posts);
    albums = findViewById(R.id.albums);
    todos = findViewById(R.id.todos);
    comments = findViewById(R.id.comments);

    posts.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(ListsBotao.this, ListsActivity.class);
        i.putExtra("op", "posts");
        startActivity(i);
      }
    });

    albums.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(ListsBotao.this, ListsActivity.class);
        i.putExtra("op", "albums");
        startActivity(i);
      }
    });

    todos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(ListsBotao.this, ListsActivity.class);
        i.putExtra("op", "todos");
        startActivity(i);
      }
    });

    comments.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(ListsBotao.this, ListsActivity.class);
        i.putExtra("op", "comments");
        startActivity(i);
      }
    });

  }
}