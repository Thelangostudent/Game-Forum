package com.group.gameforumproject;

public class Comments {

    private int postId;
    private int commentid;
    private User commentByUser;
    private String description;
    private char postType;

    public Comments (int commentId, int postId ,  User commentByUser, String description, char postType)
    {
        this.commentid = commentId;
        this.commentByUser = commentByUser;
        this.description = description;
        this.postType = postType;
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
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
