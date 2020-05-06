package com.dilipkumarg.fk.newsfeed.service;

import java.util.Collection;

import com.dilipkumarg.fk.newsfeed.models.Post;

public class NewsFeedServiceImpl implements NewsFeedService {

    private final SecurityService securityService;
    private final UserService userService;
    private final PostService postService;

    public NewsFeedServiceImpl(
            final SecurityService securityService, final UserService userService,
            final PostService postService) {
        this.securityService = securityService;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public Collection<Post> listPosts() {
        final Collection<Post> posts = postService.findAllPosts();
        // todo sorting

        return posts;
    }
}
