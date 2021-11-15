package com.group.gameforumproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position)
    {
        holder.discussionPostDescription.setText(discussionList.get(position).getDescription());
        holder.discussionPostTitle.setText(discussionList.get(position).getTitle());
        holder.discussionUserName.setText("Dummy");


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class DiscussionViewHolder extends RecyclerView.ViewHolder
    {
        ImageView discussionProfilePicture;
        TextView discussionUserName;
        TextView discussionPostTitle;
        TextView discussionPostDescription;


        public DiscussionViewHolder(View postView)
        {
            super (postView);
            discussionProfilePicture = postView.findViewById(R.id.img_userProfileOneDiscussion);
            discussionUserName = postView.findViewById(R.id.txt_OneDiscussionUserName);
            discussionPostTitle = postView.findViewById(R.id.txt_OneDiscsussionTitle);
            discussionPostDescription = postView.findViewById(R.id.txt_LatestNewsDescription);

        }
    }


}
