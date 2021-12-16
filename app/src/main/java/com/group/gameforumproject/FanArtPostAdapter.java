package com.group.gameforumproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

// code inspired by https://github.com/aws1994/BlogApp

public class FanArtPostAdapter extends RecyclerView.Adapter<FanArtPostAdapter.MyViewHolder> {

   private Context mContext;
   private List<FanArtPost> faPosts;


    public FanArtPostAdapter(Context mContext, List<FanArtPost> faPosts) {
        this.mContext = mContext;
        this.faPosts = faPosts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.one_fanart,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(faPosts.get(position).getTitle());
        holder.name.setText(faPosts.get(position).getUsername());
        Glide.with(mContext).load(faPosts.get(position).getPicture()).into(holder.imgPost);
        Glide.with(mContext).load(faPosts.get(position).getUserPhoto()).into(holder.imgPostProfile);

    }

    @Override
    public int getItemCount() {
        return faPosts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView name;
        ImageView imgPost;
        ImageView imgPostProfile;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.row_post_title);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);
            name = itemView.findViewById(R.id.row_post_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postDetailActivity = new Intent(mContext,FanArtDetailPage.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("title", faPosts.get(position).getTitle());
                    postDetailActivity.putExtra("postImage", faPosts.get(position).getPicture());
                    postDetailActivity.putExtra("description", faPosts.get(position).getDescription());
                    postDetailActivity.putExtra("postKey", faPosts.get(position).getPostKey());
                    postDetailActivity.putExtra("userPhoto", faPosts.get(position).getUserPhoto());
                    postDetailActivity.putExtra("userName", faPosts.get(position).getUsername());
                    long timestamp  = (long) faPosts.get(position).getTimestamp();
                    postDetailActivity.putExtra("postDate",timestamp) ;
                    mContext.startActivity(postDetailActivity);



                }
            });

        }


    }
}