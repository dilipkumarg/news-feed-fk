package com.dilipkumarg.fk.newsfeed;


import com.dilipkumarg.fk.newsfeed.executor.CommandLineExecutor;
import com.dilipkumarg.fk.newsfeed.factory.ObjectsFactory;

public class App {

    public static void main(String[] args) {
        ObjectsFactory objectsFactory = new ObjectsFactory();
        CommandLineExecutor executor = new CommandLineExecutor(objectsFactory);
        executor.execute();
    }
}
