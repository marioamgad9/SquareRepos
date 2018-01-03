package com.mouris.mario.squarerepos.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Repositories")
public class Repo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;
    public String owner_name;
    public String url;
    public String owner_url;
    public boolean fork;

    public Repo(String name, String description, String url,
                String owner_name, String owner_url, boolean fork) {
        this.name = name;
        this.description = description;
        this.owner_name = owner_name;
        this.url = url;
        this.owner_url = owner_url;
        this.fork = fork;
    }
}
