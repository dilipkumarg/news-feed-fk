package com.dilipkumarg.fk.newsfeed.commands;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import com.dilipkumarg.fk.newsfeed.models.Post;
import com.dilipkumarg.fk.newsfeed.service.LogService;
import com.dilipkumarg.fk.newsfeed.service.NewsFeedService;

public class NewsFeedCommand implements Command {

    private final NewsFeedService newsFeedService;
    private final LogService logService;

    public NewsFeedCommand(
            final NewsFeedService newsFeedService, final LogService logService) {
        this.newsFeedService = newsFeedService;
        this.logService = logService;
    }

    @Override
    public boolean canAccept(final String command, final List<String> args) {
        return true;
    }

    @Override
    public void execute(final List<String> arguments) {
        newsFeedService.listPosts().stream()
                .map(post -> asString(post, false))
                .forEach(logService::print);
    }

    private String asString(Post post, boolean padding) {
        StringBuilder sb = new StringBuilder();
        final long seconds = Duration.between(Instant.now(), post.getCreatedAt()).getSeconds();

        if (padding) {
            sb.append("\t");
        }
        sb.append("Id:").append(String.format("%d", post.getId())).append("\n");
        if (padding) {
            sb.append("\t");
        }
        sb.append("Content:").append(post.getContent()).append("\n");
        if (padding) {
            sb.append("\t");
        }
        sb.append("(up votes:").append(post.getVotes().getUpVotes().get()).append(", down votes:")
                .append(post.getVotes().getDownVotes().get()).append(")\n");
        if (padding) {
            sb.append("\t");
        }
        sb.append(post.getCreatedBy().getName()).append("\n");
        if (padding) {
            sb.append("\t");
        }
        sb.append(seconds).append(" seconds ago").append("\n");

        post.getComments().forEach(comment -> {
            sb.append(asString(comment, true)).append("\n");
        });


        return sb.toString();
    }
}
