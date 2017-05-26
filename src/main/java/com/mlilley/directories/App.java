package com.mlilley.directories;

import com.mlilley.directories.directories.DirectoryTree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        DirectoryTree directoryTree = new DirectoryTree();
        CommandLineManager cmdLineMgr = new CommandLineManager(directoryTree);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> stream = in.lines();

        // Convert each line of stdin to a command and execute it
        stream.forEach(line -> cmdLineMgr.parse(line).execute());
    }
}