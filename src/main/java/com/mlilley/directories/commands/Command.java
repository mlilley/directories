package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

public abstract class Command {
    protected String name;
    protected DirectoryTree directoryTree;
    protected List<String> args;
    protected PrintStream out;
    protected PrintStream err;

    public Command(String name, DirectoryTree directoryTree) {
        this.name = name;
        this.directoryTree = directoryTree;
        this.args = new ArrayList<String>();
        this.out = System.out;
        this.err = System.err;
    }

    public void withArgs(List<String> args) {
        this.args = args;
    }

    public List<String> getArgs() {
        return this.args;
    }

    public void withOutputStreams(ByteArrayOutputStream out, ByteArrayOutputStream err) {
        this.out = new PrintStream(out);
        this.err = new PrintStream(err);
    }

    public String toString() {
        String s = String.format("Command: %-8s", name.trim());
        for (String arg : args) {
            s += String.format("%-8s", arg.trim());
        }
        return s.trim();
    }

    public abstract void execute();
}