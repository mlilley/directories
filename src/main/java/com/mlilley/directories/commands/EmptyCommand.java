package com.mlilley.directories.commands;


import com.mlilley.directories.directories.DirectoryTree;

public class EmptyCommand extends Command {
    public EmptyCommand(DirectoryTree directoryTree) {
        super("(empty)", directoryTree);
    }

    public void execute() { }
}
