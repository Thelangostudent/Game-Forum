package com.group.gameforumproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiscussionPostAdapter extends RecyclerView.Adapter<DiscussionPostAdapter.DiscussionViewHolder> {

    ArrayList<DiscussionPost> discussionList;

    Context context;


    public DiscussionPostAdapter(ArrayList<DiscussionPost> discussionList, Context context)
    {
        this.discussionList = discussionList;
        this.context = context;
    }

    public DiscussionViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_discussion,parent,false);

        DiscussionViewHolder holder = new DiscussionViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class DiscussionViewHolder extends RecyclerView.ViewHolder
    {


        public DiscussionViewHolder(View postView)
        {
            super (postView);
        }
    }


}
