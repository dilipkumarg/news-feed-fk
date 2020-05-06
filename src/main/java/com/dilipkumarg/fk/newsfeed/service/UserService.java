package com.dilipkumarg.fk.newsfeed.service;

import java.util.Optional;

import com.dilipkumarg.fk.newsfeed.models.User;

public interface UserService {

    User createUser(String name);

    Optional<User> getUser(String name);

    void addFollower(User user, String otherUserName); // user & user

}
