package com.mouris.mario.squarerepos.ui;

import android.app.LoaderManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mouris.mario.squarerepos.R;
import com.mouris.mario.squarerepos.data.Repo;
import com.mouris.mario.squarerepos.utils.ReposLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Repo>>{

    private static final int LOADER_ID = 1;

    private ReposRVAdapter mAdapter;
    private MainViewModel mViewModel;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getRepos().observe(this, repoList -> {
            mAdapter.setReposList(repoList);
            if (repoList.size() == 0) {
                getLoaderManager().initLoader(LOADER_ID, null, this);
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.main_recyclerView);
        mAdapter = new ReposRVAdapter(null);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout = findViewById(R.id.main_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(() -> getLoaderManager().initLoader(LOADER_ID, null, this));

    }

    @Override
    public Loader<List<Repo>> onCreateLoader(int id, Bundle args) {
        return new ReposLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Repo>> loader, List<Repo> repos) {
        mSwipeRefreshLayout.setRefreshing(false);
        mViewModel.deleteAllRepos();
        mViewModel.insertRepos(repos);
    }

    @Override
    public void onLoaderReset(Loader<List<Repo>> loader) {

    }
}
