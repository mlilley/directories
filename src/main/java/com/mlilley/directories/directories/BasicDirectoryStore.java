package com.mlilley.directories.directories;

import java.util.*;

/**
 * BasicDirectoryStore
 * Uses an arraylist to store items in unsorted order.
 *   - inserts: O(1)
 *   - searches: O(N)
 *   - ordered enumeration: O(NlogN) (mergesort)
 */
public class BasicDirectoryStore implements DirectoryStore {
    protected List<Directory> directories;

    public BasicDirectoryStore() {
        directories = new ArrayList<Directory>();
    }

    @Override
    public void add(Directory d) {
        directories.add(d);
    }

    @Override
    public Directory get(String name) {
        for (Directory d : directories) {
            if (d.name.compareTo(name) == 0) {
                return d;
            }
        }
        return null;
    }

    @Override
    public List<Directory> dirs() {
        Collections.sort(directories);
        return directories;
    }
}
