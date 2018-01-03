package com.mouris.mario.squarerepos.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mouris.mario.squarerepos.R;
import com.mouris.mario.squarerepos.data.Repo;
import com.mouris.mario.squarerepos.utils.ReposLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Repo>>{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Repo>> onCreateLoader(int id, Bundle args) {
        return new ReposLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Repo>> loader, List<Repo> repos) {

    }

    @Override
    public void onLoaderReset(Loader<List<Repo>> loader) {

    }
}
