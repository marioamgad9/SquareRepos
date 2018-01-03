package com.mouris.mario.squarerepos.data;

public class Repo {
    public String name;
    public String description;
    public String owner_name;
    public String url;

    public Repo(String name, String description, String url, String owner_name) {
        this.name = name;
        this.description = description;
        this.owner_name = owner_name;
        this.url = url;
    }
}
