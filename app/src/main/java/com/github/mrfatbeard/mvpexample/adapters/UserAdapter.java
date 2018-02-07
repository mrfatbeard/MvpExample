package com.github.mrfatbeard.mvpexample.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.mrfatbeard.mvpexample.R;
import com.github.mrfatbeard.mvpexample.mvp.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final List<UserModel> data;
    @Nullable
    private final OnItemClickListener onItemClickListener;

    public UserAdapter(@NonNull List<UserModel> data,
                       @Nullable OnItemClickListener onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final UserModel item = data.get(position);
        if (item != null) {
            final RequestOptions ro = RequestOptions.circleCropTransform()
                    .placeholder(R.drawable.ic_person_gray_24dp);
            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(ro)
                    .load(item.getPhotoUrl())
                    .into(holder.profilePicView);
            holder.nameView.setText(item.getName());
            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        final ImageView profilePicView;
        final TextView nameView;

        UserViewHolder(View itemView) {
            super(itemView);
            profilePicView = itemView.findViewById(R.id.profilePicView);
            nameView = itemView.findViewById(R.id.nameView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserModel item);
    }
}