package com.group.gameforumproject;

public class Comments {

    private int id;
    public User commentByUser;
    public String description;
    public char postType;

    public Comments (int id, User commentByUser, String description, char postType)
    {
        this.id = id;
        this.commentByUser = commentByUser;
        this.description = description;
        this.postType = postType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCommentByUser() {
        return commentByUser;
    }

    public void setCommentByUser(User commentByUser) {
        this.commentByUser = commentByUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getPostType() {
        return postType;
    }

    public void setPostType(char postType) {
        this.postType = postType;
    }
}
