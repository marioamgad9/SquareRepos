package com.mouris.mario.squarerepos.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouris.mario.squarerepos.R;
import com.mouris.mario.squarerepos.data.Repo;

import java.util.List;


public class ReposRVAdapter extends RecyclerView.Adapter<ReposRVAdapter.RepoViewHolder>{

    List<Repo> reposList;

    ReposRVAdapter(List<Repo> reposList) {
        this.reposList = reposList;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repo, viewGroup, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder repoViewHolder, int position) {
        Repo repoItem = reposList.get(position);

        repoViewHolder.repoNameTextView.setText(repoItem.name);
        repoViewHolder.ownerNameTextView.setText(repoItem.owner_name);
    }

    @Override
    public int getItemCount() {
        if (reposList == null) return 0;
        return reposList.size();
    }

    public void setReposList(List<Repo> reposList) {
        this.reposList = reposList;
        notifyDataSetChanged();
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView repoNameTextView;
        TextView ownerNameTextView;

        RepoViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_cardView);
            repoNameTextView = itemView.findViewById(R.id.item_title);
            ownerNameTextView = itemView.findViewById(R.id.item_owner);
        }
    }

}
