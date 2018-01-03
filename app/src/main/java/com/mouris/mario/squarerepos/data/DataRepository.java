package com.mouris.mario.squarerepos.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {

    private static DataRepository sInstance;

    private ReposDao mReposDao;

    private DataRepository(ReposDao reposDao) {
        mReposDao = reposDao;
    }

    public static DataRepository getInstance(ReposDao reposDao) {
        if (sInstance == null) {
            sInstance = new DataRepository(reposDao);
        }
        return sInstance;
    }

    public LiveData<List<Repo>> getRepos() {
        return mReposDao.getRepos();
    }

    public void insertRepos(final List<Repo> reposList) {
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mReposDao.insert(reposList);
                return null;
            }
        }.execute();
    }

    public void deleteRepos() {
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mReposDao.deleteAll();
                return null;
            }
        }.execute();
    }

}
