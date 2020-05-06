package com.dilipkumarg.fk.newsfeed.service;

import java.util.Optional;

import com.dilipkumarg.fk.newsfeed.models.User;
import com.dilipkumarg.fk.newsfeed.repo.UserRepository;

public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(final String name) {
        User user = new User(name);
        repository.save(name, user);
        return user;
    }

    @Override
    public Optional<User> getUser(final String name) {
        return repository.findById(name);
    }

    @Override
    public void addFollower(final User user, final String otherUserName) {
        final User otherUser = repository.findById(otherUserName)
                .orElseThrow(() -> new IllegalArgumentException("Other User not exists:" + otherUserName));
        user.getFollowingUsers().add(otherUser);
    }


}
