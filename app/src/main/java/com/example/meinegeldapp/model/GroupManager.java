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
        groups.add(new Group("Einkauf", 1));
        groups.add(new Group("Ausgehen", 2));
        groups.add(new Group("Tanken", 3));
        groups.add(new Group("Geschenke", 4));
        groups.add(new Group("Freizeit", 5));
    }

    public Group getGroupByName(String name) {

        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }

        return null;
    }

    public Group getGroupById(int id) {

        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }

        return null;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
