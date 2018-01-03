package com.mouris.mario.squarerepos.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Repo> repoList);

    @Query("SELECT * FROM Repositories")
    LiveData<List<Repo>> getRepos();

    @Query("DELETE FROM Repositories")
    void deleteAll();
}
