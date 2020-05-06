package com.dilipkumarg.fk.newsfeed.service;

import java.util.Collection;

import com.dilipkumarg.fk.newsfeed.models.Post;

public interface PostService {

    Post createPost(String content);

    Post addComment(Integer postId, String comment);

    Post addUpvote(Integer postId);

    Post addDownVote(Integer postId);

    Collection<Post> findAllPosts(); // add pagination

}
