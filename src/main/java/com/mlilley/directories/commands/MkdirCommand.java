package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import com.mlilley.directories.exceptions.DirectoryAlreadyExistsException;

public class MkdirCommand extends Command {
    public MkdirCommand(DirectoryTree directoryTree) {
        super("mkdir", directoryTree);
    }

    public void execute() {
        out.println(toString());

        if (args.size() != 1) {
            err.println("Error: mkdir takes 1 argument");
            return;
        }

        try {
            directoryTree.createSubdirectory(args.get(0));
        } catch (DirectoryAlreadyExistsException ex) {
            err.println("Subdirectory already exists");
        }
    }
}