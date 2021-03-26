package com.example.apiactivity.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Todos implements Parcelable {

  private int userId;
  private int id;
  private String title;
  private Boolean completed;

  public Todos(int userId, int id, String title, Boolean completed) {
    this.userId = userId;
    this.id = id;
    this.title = title;
    this.completed = completed;
  }

  @RequiresApi(api = Build.VERSION_CODES.Q)
  private Todos(Parcel p) {
    this.userId = p.readInt();
    this.id = p.readInt();
    this.title = p.readString();
    this.completed = p.readBoolean();
  }

  public static final Creator<Todos> CREATOR = new Creator<Todos>() {
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public Todos createFromParcel(Parcel p) {
      return new Todos(p);
    }

    @Override
    public Todos[] newArray(int size) {
      return new Todos[size];
    }
  };

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @RequiresApi(api = Build.VERSION_CODES.Q)
  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(this.userId);
    parcel.writeInt(this.id);
    parcel.writeString(this.title);
    parcel.writeBoolean(this.completed);
  }

}
