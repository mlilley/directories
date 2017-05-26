package com.mlilley.directories.directories;


import java.util.Iterator;

public interface DirectoryStore {
    void add(Directory d);
    Directory get(String name);
    Iterator<Directory> iterator();
}
