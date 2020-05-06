package com.dilipkumarg.fk.newsfeed.service;

import java.util.Collection;

import com.dilipkumarg.fk.newsfeed.models.Post;

public interface NewsFeedService {

    Collection<Post> listPosts();
}
