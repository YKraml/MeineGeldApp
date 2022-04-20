package com.example.meinegeldapp.model;

import java.util.ArrayList;
import java.util.List;

public class GroupManager {

    private final List<Group> groups;

    private static GroupManager instance;

    public static GroupManager getInstance() {
        if (instance == null) {
            instance = new GroupManager();
            // TODO: 20.04.2022 Delete the following line
            instance.fillWithDummyData();
        }
        return instance;
    }

    private GroupManager() {
        groups = new ArrayList<>();
    }

    private void fillWithDummyData() {
        groups.add(new Group("Einkauf"));
        groups.add(new Group("Ausgehen"));
        groups.add(new Group("Tanken"));
        groups.add(new Group("Geschenke"));
        groups.add(new Group("Freizeit"));
    }

    public List<Group> getGroups() {
        return groups;
    }
}
