package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import com.mlilley.directories.exceptions.CannotMoveUpFromRootDirectoryException;

public class UpCommand extends Command {
    public UpCommand(DirectoryTree directoryTree) {
        super("up", directoryTree);
    }

    public void execute() {
        out.println(toString());

        if (args.size() != 0) {
            err.println("Error: up takes no arguments");
            return;
        }

        try {
            directoryTree.navigateToParentDirectory();
        } catch (CannotMoveUpFromRootDirectoryException ex) {
            err.println("Cannot move up from root directory");
        }
    }
}
