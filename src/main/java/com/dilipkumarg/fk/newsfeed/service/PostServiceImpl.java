package com.dilipkumarg.fk.newsfeed.service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import com.dilipkumarg.fk.newsfeed.models.Post;
import com.dilipkumarg.fk.newsfeed.repo.PostRepository;

public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final SecurityService securityService;

    private final AtomicInteger postIdentifier = new AtomicInteger(0);

    public PostServiceImpl(
            final PostRepository repository, final SecurityService securityService) {
        this.repository = repository;
        this.securityService = securityService;
    }

    @Override
    public Post createPost(final String content) {
        final Post newPost = Post.builder()
                .id(postIdentifier.incrementAndGet())
                .content(content)
                .createdBy(securityService.getLoggedInUser())
                .build();
        return repository.save(newPost.getId(), newPost);
    }

    @Override
    public Post addComment(final Integer postId, final String comment) {
        Post parenPost = getPost(postId);

        synchronized (parenPost) {
            int id = parenPost.getComments().size() + 1;
            parenPost.getComments().add(Post.builder()
                    .id(id)
                    .content(comment)
                    .createdBy(securityService.getLoggedInUser())
                    .build());
        }

        return parenPost;
    }

    @Override
    public Post addUpvote(final Integer postId) {
        final Post post = getPost(postId);
        post.getVotes().getUpVotes().incrementAndGet();
        return post;
    }

    @Override
    public Post addDownVote(final Integer postId) {
        final Post post = getPost(postId);
        post.getVotes().getDownVotes().incrementAndGet();
        return post;
    }

    @Override
    public Collection<Post> findAllPosts() {
        return repository.findAll();
    }

    private Post getPost(final Integer postId) {
        return repository.findById(postId).orElseThrow(() -> new IllegalArgumentException("No post exists " +
                "with id:" + postId));
    }
}
