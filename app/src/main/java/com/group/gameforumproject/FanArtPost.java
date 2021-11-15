package com.group.gameforumproject;

import com.google.firebase.database.ServerValue;

public class FanArtPost {

    private int id;
    private String postKey;
    private String title;
    private User user;
    private String image;
    private Object timestamp;

    public FanArtPost (int id, String title, User user, String image )
    {
        this.id = id;
        this.title = title;
        this.user = user;
        this.image = image;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public FanArtPost(){}

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
