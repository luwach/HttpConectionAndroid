package com.example.lwach.notelist.model;

public class Note {

    //GET details

    private String id;
    private String name;
    private String content;

    public Note(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
