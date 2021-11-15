package com.group.gameforumproject;

public class DiscussionPost {

    private int id;
    public String title;
    public String description;
    public Comments discussionComments;


    public DiscussionPost (int id, String title, String description, Comments discussionComments)
    {
        this.id = id;
        this.title = title;
        this.description = description;
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
