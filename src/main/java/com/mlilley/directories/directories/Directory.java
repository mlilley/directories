package com.mlilley.directories.directories;


public class Directory implements Comparable<Directory> {
    protected String name;
    protected Directory parent;
    protected BSTDirectoryStore subdirectories;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.subdirectories = new BSTDirectoryStore();
    }

    @Override
    public int compareTo(Directory dir) {
        return this.name.compareTo(dir.name);
    }
}
