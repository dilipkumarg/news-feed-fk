package com.dilipkumarg.fk.newsfeed.factory;

import com.dilipkumarg.fk.newsfeed.repo.PostRepository;
import com.dilipkumarg.fk.newsfeed.repo.PostRepositoryImpl;
import com.dilipkumarg.fk.newsfeed.repo.UserRepository;
import com.dilipkumarg.fk.newsfeed.repo.UserRepositoryImpl;
import com.dilipkumarg.fk.newsfeed.service.LogService;
import com.dilipkumarg.fk.newsfeed.service.NewsFeedService;
import com.dilipkumarg.fk.newsfeed.service.NewsFeedServiceImpl;
import com.dilipkumarg.fk.newsfeed.service.PostService;
import com.dilipkumarg.fk.newsfeed.service.PostServiceImpl;
import com.dilipkumarg.fk.newsfeed.service.SecurityService;
import com.dilipkumarg.fk.newsfeed.service.SystemOutLogService;
import com.dilipkumarg.fk.newsfeed.service.UserService;
import com.dilipkumarg.fk.newsfeed.service.UserServiceImpl;
import lombok.Data;

// for time being creating all instances over here
@Data
public class ObjectsFactory {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final UserService userService = new UserServiceImpl(userRepository);

    private final SecurityService securityService = new SecurityService(userService);


    private final PostRepository postRepository = new PostRepositoryImpl();
    private final PostService postService = new PostServiceImpl(postRepository, securityService);

    private final NewsFeedService newsFeedService = new NewsFeedServiceImpl(securityService, userService, postService);

    private final LogService logService = new SystemOutLogService();
}
