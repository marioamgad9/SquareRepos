package com.mouris.mario.squarerepos.ui;

import android.app.LoaderManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Loader;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mouris.mario.squarerepos.R;
import com.mouris.mario.squarerepos.data.Repo;
import com.mouris.mario.squarerepos.utils.ReposLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Repo>>{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 1;

    private ReposRVAdapter mAdapter;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getRepos().observe(this, repoList -> {
            mAdapter.setReposList(repoList);
        });

        getLoaderManager().initLoader(LOADER_ID, null, this);

        RecyclerView recyclerView = findViewById(R.id.main_recyclerView);
        mAdapter = new ReposRVAdapter(null);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public Loader<List<Repo>> onCreateLoader(int id, Bundle args) {
        return new ReposLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Repo>> loader, List<Repo> repos) {
        mViewModel.deleteAllRepos();
        mViewModel.insertRepos(repos);
    }

    @Override
    public void onLoaderReset(Loader<List<Repo>> loader) {

    }
}
