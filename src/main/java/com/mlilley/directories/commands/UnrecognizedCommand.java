package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;

public class UnrecognizedCommand extends Command {
    public UnrecognizedCommand(DirectoryTree directoryTree, String name) {
        super(name, directoryTree);
    }

    public void execute() {
        out.println(toString());

        err.println("Error: unrecognized command");
    }
}