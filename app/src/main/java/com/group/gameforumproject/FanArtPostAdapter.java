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

public class FanArtPostAdapter extends RecyclerView.Adapter<FanArtPostAdapter.MyViewHolder> {

   private Context mContext;
   private List<FanArtPost> mData ;


    public FanArtPostAdapter(Context mContext, List<FanArtPost> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.one_fanart,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.name.setText(mData.get(position).getUsername());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);
        Glide.with(mContext).load(mData.get(position).getUserPhoto()).into(holder.imgPostProfile);

    }

    @Override
    public int getItemCount() {
        return mData.size();
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

                    postDetailActivity.putExtra("title",mData.get(position).getTitle());
                    postDetailActivity.putExtra("postImage",mData.get(position).getPicture());
                    postDetailActivity.putExtra("description",mData.get(position).getDescription());
                    postDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    postDetailActivity.putExtra("userPhoto",mData.get(position).getUserPhoto());
                    postDetailActivity.putExtra("userName",mData.get(position).getUsername());
                    long timestamp  = (long) mData.get(position).getTimestamp();
                    postDetailActivity.putExtra("postDate",timestamp) ;
                    mContext.startActivity(postDetailActivity);



                }
            });

        }


    }
}