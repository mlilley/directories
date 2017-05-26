package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import com.mlilley.directories.exceptions.DirectoryNotFoundException;

public class CdCommand extends Command {
    public CdCommand(DirectoryTree directoryTree) {
        super("cd", directoryTree);
    }

    public void execute() {
        out.println(toString());

        if (args.size() != 1) {
            err.println("Error: cd takes 1 argument");
            return;
        }

        try {
            directoryTree.navigateToSubdirectory(args.get(0));
        } catch (DirectoryNotFoundException ex) {
            err.println("Subdirectory does not exist");
        }
    }
}