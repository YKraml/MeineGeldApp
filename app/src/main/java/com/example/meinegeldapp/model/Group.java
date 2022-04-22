package com.example.meinegeldapp.model;

public class Group {

    private String name;
    private final int id;

    public Group(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
