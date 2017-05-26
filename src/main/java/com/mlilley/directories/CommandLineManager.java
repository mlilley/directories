package com.mlilley.directories;

import com.mlilley.directories.commands.*;
import com.mlilley.directories.directories.DirectoryTree;

import java.util.Arrays;
import java.util.List;

public class CommandLineManager {
    protected DirectoryTree directoryTree;

    public CommandLineManager(DirectoryTree directoryTree) {
        this.directoryTree = directoryTree;
    }

    /**
     * Parses a line of console input and returns the corresponding command,
     * or either EmptyCommand or UnrecognizedCommand instead.
     * @param line
     * @return Command
     */
    public Command parse(String line) {
        String[] bits = line.trim().split("[ ]+");
        Command cmd;

        switch (bits[0]) {
            case "dir":
                cmd = new DirCommand(directoryTree);
                break;
            case "up":
                cmd = new UpCommand(directoryTree);
                break;
            case "mkdir":
                cmd = new MkdirCommand(directoryTree);
                break;
            case "cd":
                cmd = new CdCommand(directoryTree);
                break;
            case "":
                cmd = new EmptyCommand(directoryTree);
                break;
            default:
                cmd = new UnrecognizedCommand(directoryTree, bits[0]);
        }

        if (bits.length > 1) {
            List<String> args = Arrays.asList(bits).subList(1, bits.length);
            cmd.withArgs(args);
        }

        return cmd;
    }
}
