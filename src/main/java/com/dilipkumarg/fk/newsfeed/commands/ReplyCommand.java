package com.dilipkumarg.fk.newsfeed.commands;

import java.util.List;

import com.dilipkumarg.fk.newsfeed.service.PostService;

public class ReplyCommand implements Command {

    private final PostService postService;

    public ReplyCommand(final PostService postService) {
        this.postService = postService;
    }

    @Override
    public boolean canAccept(final String command, final List<String> args) {
        return args.size() == 2;
    }

    @Override
    public void execute(final List<String> arguments) {
        postService.addComment(Integer.parseInt(arguments.get(0)), arguments.get(1));
    }
}
