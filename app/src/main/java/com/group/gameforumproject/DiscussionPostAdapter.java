package com.group.gameforumproject;

import android.content.Context;

import java.util.ArrayList;

public class DiscussionPostAdapter {

    ArrayList<DiscussionPost> discussionList;

    Context context;


    public DiscussionPostAdapter(ArrayList<DiscussionPost> discussionList, Context context)
    {
        this.discussionList = discussionList;
        this.context = context;
    }



}
