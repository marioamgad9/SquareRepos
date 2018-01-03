package com.mouris.mario.squarerepos.utils;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.mouris.mario.squarerepos.data.Repo;

import java.util.List;

public class ReposLoader extends AsyncTaskLoader<List<Repo>>{

    public ReposLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Repo> loadInBackground() {
        return QueryUtils.fetchReposData();
    }
}
