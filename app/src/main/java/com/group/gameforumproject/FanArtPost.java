package com.group.gameforumproject;

public class FanArtPost {

    private int id;
    private String title;
    private User user;
    private String image;

    public FanArtPost (int id, String title, User user, String image )
    {
        this.id = id;
        this.title = title;
        this.user = user;
        this.image = image;
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
