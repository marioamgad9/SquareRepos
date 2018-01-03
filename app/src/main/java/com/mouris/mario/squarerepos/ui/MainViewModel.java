package com.mouris.mario.squarerepos.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mouris.mario.squarerepos.data.DataRepository;
import com.mouris.mario.squarerepos.data.Repo;
import com.mouris.mario.squarerepos.data.ReposDao;
import com.mouris.mario.squarerepos.data.ReposDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel{

    private DataRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        ReposDao dao = ReposDatabase.getInstance(application).repoDao();
        mRepository = DataRepository.getInstance(dao);
    }

    public LiveData<List<Repo>> getRepos() {
        return mRepository.getRepos();
    }

    public void insertRepos(List<Repo> reposList) {
        mRepository.insertRepos(reposList);
    }

    public void deleteAllRepos() {
        mRepository.deleteRepos();
    }

}
