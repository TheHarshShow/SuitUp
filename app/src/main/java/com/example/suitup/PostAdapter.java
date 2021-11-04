package com.example.suitup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<Post> PostList;

    private Context c;

    public PostAdapter(ArrayList<Post> postList, Context c) {
        PostList = postList;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.post_model, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.usernameTextView.setText(PostList.get(position).getUsername());
        holder.likesTextView.setText(String.valueOf(PostList.get(position).getLiked().size()));
        holder.priceTextView.setText(PostList.get(position).getPrice());
        Picasso.get().load(PostList.get(position).getImage_url()).into(holder.postImageView);
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameTextView;
        public TextView likesTextView;
        public TextView priceTextView;
        public ImageView postImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username_text_view);
            likesTextView = itemView.findViewById(R.id.likes_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            postImageView = itemView.findViewById(R.id.post_image_view);
        }
    }

}
