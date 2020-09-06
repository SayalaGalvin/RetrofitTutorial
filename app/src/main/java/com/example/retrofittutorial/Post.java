package com.example.retrofittutorial;

//Plain Old Java Objects (POJO) class
public class Post {
    private Integer id;
    private String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Post(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
