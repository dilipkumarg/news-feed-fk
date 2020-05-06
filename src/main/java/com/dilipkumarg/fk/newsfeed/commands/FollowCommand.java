package com.dilipkumarg.fk.newsfeed.commands;

import java.util.List;

import com.dilipkumarg.fk.newsfeed.service.SecurityService;
import com.dilipkumarg.fk.newsfeed.service.UserService;

public class FollowCommand implements Command {

    private final SecurityService securityService;
    private final UserService userService;

    public FollowCommand(
            final SecurityService securityService, final UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @Override
    public boolean canAccept(final String command, final List<String> args) {
        return args.size() == 1;
    }

    @Override
    public void execute(final List<String> arguments) {
        userService.addFollower(securityService.getLoggedInUser(), arguments.get(0));
    }
}
