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

public class CommentsAdapter  extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    ArrayList<Comments> commentsList;


    Context context;


    public CommentsAdapter(ArrayList<Comments> commentsList, Context context)
    {
        this.commentsList = commentsList;
        this.context = context;
    }

    public CommentsViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_comment,parent,false);

        CommentsViewHolder holder = new CommentsViewHolder(view);

        System.out.println(commentsList.size());

        System.out.println(commentsList.get(0).getDescription());

        return holder;

    }



    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position)
    {
        holder.txt_commentDescription.setText(commentsList.get(position).getDescription());
        holder.txt_commentUserName.setText(commentsList.get(position).getCommentByUser().getUsername());


    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }





    public class CommentsViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_commentUserName;

        TextView txt_commentDescription;


        public CommentsViewHolder(View itemView)
        {
            super (itemView);

            txt_commentUserName = itemView.findViewById(R.id.txt_commentUserName);
            txt_commentDescription = itemView.findViewById(R.id.txt_commentDescription);



        }
    }

}
