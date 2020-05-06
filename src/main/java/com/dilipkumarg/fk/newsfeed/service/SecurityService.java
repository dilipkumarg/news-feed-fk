package com.dilipkumarg.fk.newsfeed.service;

import java.util.Optional;

import com.dilipkumarg.fk.newsfeed.models.User;

public class SecurityService {

    private ThreadLocal<User> userTL = new ThreadLocal<>();

    private UserService userService;

    public SecurityService(final UserService userService) {
        this.userService = userService;
    }

    public void login(String name) {
        userTL.remove();
        final Optional<User> user = userService.getUser(name);
        if (user.isPresent()) {
            userTL.set(user.get());
        } else {
            throw new IllegalArgumentException("No user found with name:" + name);
        }
    }

    public User getLoggedInUser() {
        return finLoggedInUser().orElseThrow(() -> new IllegalStateException("No active login session found"));
    }

    public Optional<User> finLoggedInUser() {
        return Optional.ofNullable(userTL.get());
    }
}
