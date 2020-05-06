package com.dilipkumarg.fk.newsfeed.commands;

import java.util.Collections;
import java.util.List;

import com.dilipkumarg.fk.newsfeed.service.SecurityService;

public class LoginCommand implements Command {

    private final SecurityService securityService;
    private final NewsFeedCommand newsFeedCommand;

    public LoginCommand(
            final SecurityService securityService,
            final NewsFeedCommand newsFeedCommand) {
        this.securityService = securityService;
        this.newsFeedCommand = newsFeedCommand;
    }

    @Override
    public boolean canAccept(final String command, final List<String> args) {
        return args.size() == 1;
    }

    @Override
    public void execute(final List<String> arguments) {
        securityService.login(arguments.get(0));
        newsFeedCommand.execute(Collections.emptyList());
    }
}
