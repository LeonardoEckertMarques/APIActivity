package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.apiactivity.model.Albums;
import com.example.apiactivity.model.Comments;
import com.example.apiactivity.model.Posts;
import com.example.apiactivity.model.Todos;

public class DetalhesActivity extends AppCompatActivity {

  private TextView tv;
  private String op;
  private CheckBox cb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detalhes);

    op = getIntent().getStringExtra("op");

    switch(op) {
      case "posts":
        Posts posts = getIntent().getParcelableExtra("objTp");
        bindPosts(posts);
        break;
      case "albums":
        Albums albums = getIntent().getParcelableExtra("objTp");
        bindAlbums(albums);
        break;
      case "todos":
          Todos todos = getIntent().getParcelableExtra("objTp");
          bindTodos(todos);
        break;
      case "comments":
        Comments comments = getIntent().getParcelableExtra("objTp");
        bindComments(comments);
        break;
    }

  }

  public void cbClick(View v) {
    cb = findViewById(R.id.cbCompleted);
    Todos todos = getIntent().getParcelableExtra("objTp");
    todos.setCompleted(cb.isChecked());
  }

  private void bindPosts(Posts obj) {
    tv = findViewById(R.id.userId);
    tv.setText("UserID: "+obj.getUserId()+"");
    tv = findViewById(R.id.postId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setText("Title: " + obj.getTitle()+"");
    tv = findViewById(R.id.name);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.email);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.body);
    tv.setText("Body: "+obj.getBody()+"");
    cb = findViewById(R.id.cbCompleted);
    cb.setVisibility(View.GONE);
  }

  private void bindAlbums(Albums obj) {
    tv = findViewById(R.id.userId);
    tv.setText("UserID: "+obj.getUserId()+"");
    tv = findViewById(R.id.postId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setText("Title: " + obj.getTitle()+"");
    tv = findViewById(R.id.name);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.email);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.body);
    tv.setVisibility(View.GONE);
    cb = findViewById(R.id.cbCompleted);
    cb.setVisibility(View.GONE);
  }

  private void bindTodos(Todos obj) {
    tv = findViewById(R.id.userId);
    tv.setText("UserID: "+obj.getUserId()+"");
    tv = findViewById(R.id.postId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.id);
    tv.setText("ID: "+ obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setText("Title: "+ obj.getTitle());
    tv = findViewById(R.id.name);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.email);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.body);
    tv.setVisibility(View.GONE);
    cb = findViewById(R.id.cbCompleted);
    cb.setChecked(obj.getCompleted());
  }

  private void bindComments(Comments obj) {
    tv = findViewById(R.id.userId);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.postId);
    tv.setText("PostID: "+obj.getPostId()+"");
    tv = findViewById(R.id.id);
    tv.setText("ID: "+obj.getId()+"");
    tv = findViewById(R.id.title);
    tv.setVisibility(View.GONE);
    tv = findViewById(R.id.name);
    tv.setText("Name: "+obj.getName()+"");
    tv = findViewById(R.id.email);
    tv.setText("Email: "+obj.getEmail()+"");
    tv = findViewById(R.id.body);
    tv.setText("Body: "+obj.getBody()+"");
    cb = findViewById(R.id.cbCompleted);
    cb.setVisibility(View.GONE);
  }

}