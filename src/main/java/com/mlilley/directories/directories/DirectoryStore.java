package com.mlilley.directories.directories;


import java.util.List;

public interface DirectoryStore {
    void add(Directory d);
    Directory get(String name);
    List<Directory> dirs();
}
