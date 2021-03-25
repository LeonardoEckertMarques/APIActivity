package com.example.apiactivity.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Albums implements Parcelable {

  private int userId;
  private int id;
  private String title;

  public Albums(int userId, int id, String title) {
    this.userId = userId;
    this.id = id;
    this.title = title;
  }

  private Albums(Parcel p) {
    this.userId = p.readInt();
    this.id = p.readInt();
    this.title = p.readString();
  }

  public static final Creator<Albums> CREATOR = new Creator<Albums>() {
    @Override
    public Albums createFromParcel(Parcel p) {
      return new Albums(p);
    }

    @Override
    public Albums[] newArray(int size) {
      return new Albums[size];
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(this.userId);
    parcel.writeInt(this.id);
    parcel.writeString(this.title);;
  }

}
