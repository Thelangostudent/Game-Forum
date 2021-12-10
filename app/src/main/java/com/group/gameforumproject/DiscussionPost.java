package com.group.gameforumproject;

import java.util.ArrayList;

public class DiscussionPost {

    private int id;
    private String title;
    private String description;
    private ArrayList <Comments> discussionComments;
    private User user;



    public DiscussionPost (int id, String title, String description, ArrayList<Comments> discussionComments, User user)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.discussionComments = discussionComments;
        this.user = user;

    }

    public DiscussionPost(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList <Comments> getDiscussionComments() {
        return discussionComments;
    }

    public void setDiscussionComments(ArrayList<Comments> discussionComments) {
        this.discussionComments = discussionComments;
    }

    /**
     * returns the type of post this class is.
     * Used for comments to separate FanartComments and DiscussionComments
     * 'D' = discussiontype and 'F' = FanArtType;
     *
     * */
    public char postType()
    {
        char postype = 'D';

        return postype;
    }
}
