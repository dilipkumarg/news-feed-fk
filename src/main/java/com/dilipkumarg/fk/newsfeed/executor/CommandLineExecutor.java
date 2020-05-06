package com.dilipkumarg.fk.newsfeed.executor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dilipkumarg.fk.newsfeed.commands.Command;
import com.dilipkumarg.fk.newsfeed.commands.FollowCommand;
import com.dilipkumarg.fk.newsfeed.commands.LoginCommand;
import com.dilipkumarg.fk.newsfeed.commands.NewsFeedCommand;
import com.dilipkumarg.fk.newsfeed.commands.PostCommand;
import com.dilipkumarg.fk.newsfeed.commands.ReplyCommand;
import com.dilipkumarg.fk.newsfeed.commands.SignupCommand;
import com.dilipkumarg.fk.newsfeed.commands.UpvoteCommand;
import com.dilipkumarg.fk.newsfeed.factory.ObjectsFactory;

public class CommandLineExecutor {

    private final ObjectsFactory objects;

    private Map<String, Command> commands = new LinkedHashMap<>();

    public CommandLineExecutor(final ObjectsFactory objects) {
        this.objects = objects;

        commands.put("post", new PostCommand(objects.getPostService()));
        commands.put("follow", new FollowCommand(objects.getSecurityService(), objects.getUserService()));
        commands.put("reply", new ReplyCommand(objects.getPostService()));
        commands.put("upvote", new UpvoteCommand(objects.getPostService()));
        commands.put("downvote", new UpvoteCommand(objects.getPostService()));

        final NewsFeedCommand newsFeedCommand = new NewsFeedCommand(objects.getNewsFeedService(),
                objects.getLogService());
        final LoginCommand loginCommand = new LoginCommand(objects.getSecurityService(), newsFeedCommand);

        commands.put("shownewsfeed", newsFeedCommand);
        commands.put("login", loginCommand);
        commands.put("signup", new SignupCommand(objects.getUserService(), loginCommand));
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                final String line = scanner.nextLine();
                Instruction instruction = parse(line);

                if (commands.containsKey(instruction.getCommand())) {
                    final Command command = commands.get(instruction.getCommand());
                    if (command.canAccept(instruction.getCommand(), instruction.getArguments())) {
                        command.execute(instruction.getArguments());
                    } else {
                        throw new IllegalArgumentException(
                                "Invalid arguments, command not accepting instruction:" + instruction.getCommand());
                    }
                } else {
                    throw new IllegalArgumentException("Unknown command:" + instruction.getCommand());
                }
            } catch (Exception e) {// not a correct way, but
                e.printStackTrace();
            }
        }
    }

    private Instruction parse(String line) {
        final String[] words = line.split("\\~");

        List<String> argumens = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            argumens.add(words[i]);
        }

        return new Instruction(words[0], argumens);
    }


}
