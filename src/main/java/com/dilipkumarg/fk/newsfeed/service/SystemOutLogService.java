package com.dilipkumarg.fk.newsfeed.service;

public class SystemOutLogService implements LogService {
    @Override
    public void print(final String content) {
        System.out.println(content);
    }
}
