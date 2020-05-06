package com.dilipkumarg.fk.newsfeed.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private String name;

    private List<User> followingUsers = new ArrayList<>();

    public User(final String name) {
        this.name = name;
    }
}
