package com.dilipkumarg.fk.newsfeed.commands;

import java.util.List;

import com.dilipkumarg.fk.newsfeed.service.UserService;

public class SignupCommand implements Command {

    private final UserService userService;
    private final LoginCommand loginCommand;

    public SignupCommand(
            final UserService userService, final LoginCommand loginCommand) {
        this.userService = userService;
        this.loginCommand = loginCommand;
    }


    @Override
    public boolean canAccept(final String command, final List<String> args) {
        return args.size() == 1;
    }

    @Override
    public void execute(final List<String> arguments) {
        userService.createUser(arguments.get(0));
        loginCommand.execute(arguments);
    }
}
