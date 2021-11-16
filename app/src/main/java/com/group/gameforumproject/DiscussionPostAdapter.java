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

        System.out.println(discussionList.size());

        System.out.println(discussionList.get(0).getDescription());

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position)
    {
        holder.txt_OneDiscsussionDescription.setText(discussionList.get(position).getDescription());
        holder.txt_OneDiscsussionTitle.setText(discussionList.get(position).getTitle());
        holder.txt_OneDiscussionUserName.setText("Dummy");


    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }


    public class DiscussionViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_userProfileOneDiscussion;
        TextView txt_OneDiscussionUserName;
        TextView txt_OneDiscsussionTitle;
        TextView txt_OneDiscsussionDescription;


        public DiscussionViewHolder(View itemView)
        {
            super (itemView);
            img_userProfileOneDiscussion = itemView.findViewById(R.id.img_userProfileOneDiscussion);
            txt_OneDiscussionUserName = itemView.findViewById(R.id.txt_OneDiscussionUserName);
            txt_OneDiscsussionTitle = itemView.findViewById(R.id.txt_OneDiscsussionTitle);
            txt_OneDiscsussionDescription = itemView.findViewById(R.id.txt_LatestNewsDescription);


        }
    }


}
