package com.group.gameforumproject;

import com.google.firebase.database.ServerValue;

public class FanArtPost {

    private int id;
    private String postKey;
    private String title;
    private String userId;
    private String picture;
    private String description;
    private String userPhoto;
    private Object timestamp;

    public FanArtPost (int id, String title, String description, String userId, String picture, String userPhoto)
    {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.picture = picture;
        this.userPhoto = userPhoto;
        this.timestamp = ServerValue.TIMESTAMP;
        this.description = description;
    }

    public FanArtPost(){}

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPicture() {
        return picture;
    }



    public void setPicture(String picture) {
        this.picture = picture;
    }


    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
