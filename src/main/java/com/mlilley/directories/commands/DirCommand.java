package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import java.util.List;

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
        List<String> subdirectories = directoryTree.subdirectories();

        out.println("Directory of " + currentPath + ":");
        if (subdirectories.isEmpty()) {
            out.println("No subdirectories");
        } else {
            for (int i = 0, n = subdirectories.size(); i < n; i++) {
                String subdirectory = String.format("%-"+DIR_WIDTH+"s", subdirectories.get(i));
                // remove trailing spaces, add \n if arg is last on line or last of all
                if ((i + 1) % DIRS_PER_LINE == 0 || i == n - 1) {
                    subdirectory = subdirectory.trim() + "\n";
                }
                out.print(subdirectory);
            }
        }
    }
}
