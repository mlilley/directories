package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;

import java.util.Iterator;

public class DirCommand extends Command {
    public static final int DIR_WIDTH = 8;
    public static final int DIRS_PER_LINE = 10;

    public DirCommand(DirectoryTree directoryTree) {
        super("dir", directoryTree);
    }

    public void execute() {
        out.println(toString());

        if (args.size() != 0) {
            err.println("Error: dir takes no arguments");
            return;
        }

        String currentPath = directoryTree.currentPath();
        Iterator<String> subdirectories = directoryTree.subdirectories();

        out.println("Directory of " + currentPath + ":");
        if (!subdirectories.hasNext()) {
            out.println("No subdirectories");
        } else {
            String subdirectory;
            int i = 0;
            while(subdirectories.hasNext()) {
                subdirectory = String.format("%-"+DIR_WIDTH+"s", subdirectories.next());
                // remove trailing spaces, add \n if arg is last on line or last arg
                if ((i + 1) % DIRS_PER_LINE == 0 || !subdirectories.hasNext()) {
                    subdirectory = subdirectory.trim() + "\n";
                }
                out.print(subdirectory);
            }
        }
    }
}
