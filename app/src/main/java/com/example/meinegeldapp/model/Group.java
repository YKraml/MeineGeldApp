package com.example.meinegeldapp.model;

import java.io.Serializable;
import java.util.UUID;

public class Group implements Serializable {

    private final String id;
    private String name;

    public Group(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
