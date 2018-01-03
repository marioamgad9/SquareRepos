package com.mouris.mario.squarerepos.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouris.mario.squarerepos.R;
import com.mouris.mario.squarerepos.data.Repo;

import java.util.List;


public class ReposRVAdapter extends RecyclerView.Adapter<ReposRVAdapter.RepoViewHolder> {

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

        repoViewHolder.bindData(repoItem);
    }

    @Override
    public int getItemCount() {
        if (reposList == null) return 0;
        return reposList.size();
    }

    void setReposList(List<Repo> reposList) {
        this.reposList = reposList;
        notifyDataSetChanged();
    }


    static class RepoViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView repoNameTextView;
        TextView ownerNameTextView;
        TextView descriptionTextView;

        RepoViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_cardView);
            repoNameTextView = itemView.findViewById(R.id.item_title);
            ownerNameTextView = itemView.findViewById(R.id.item_owner);
            descriptionTextView = itemView.findViewById(R.id.item_description);
        }

        void bindData(final Repo repo) {
            repoNameTextView.setText(repo.name);
            ownerNameTextView.setText(repo.owner_name);
            descriptionTextView.setText(repo.description);
            if (repo.fork) {
                cardView.setCardBackgroundColor(
                        cardView.getContext().getResources().getColor(R.color.lightGreen));
            } else {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showGoToDialog(v.getContext(), repo);
                    return false;
                }
            });
        }

        private void showGoToDialog(final Context context, final Repo repo) {
            CharSequence options[] = new CharSequence[]{"Go to Owner page", "Go to Repository page"};
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Go to:");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (which == 0) {
                        intent.setData(Uri.parse(repo.owner_url));
                    } else {
                        intent.setData(Uri.parse(repo.url));
                    }
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
