package com.mouris.mario.squarerepos.utils;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.mouris.mario.squarerepos.data.Repo;

import java.util.List;

public class ReposLoader extends AsyncTaskLoader<List<Repo>>{

    private int page;

    public ReposLoader(Context context, int page) {
        super(context);
        this.page = page;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Repo> loadInBackground() {
        return QueryUtils.fetchReposData(page);
    }

    public void setPage(int page) {
        this.page = page;
    }
}
