package com.mlilley.directories.directories;

import java.util.*;

/**
 * SortedDirectoryStore
 * Uses an arraylist to store items in sorted order.
 *   - inserts: O(N + logN) (binarysearch + shift down)
 *   - searches: O(logN) (binarysearch)
 *   - ordered enumeration: O(1) (already sorted)
 */
public class SortedDirectoryStore implements DirectoryStore {
    protected List<Directory> directories;

    public SortedDirectoryStore() {
        directories = new ArrayList<Directory>();
    }

    @Override
    public void add(Directory d) {
        int i = rank(d.name);
        if (i < directories.size() && directories.get(i).name.compareTo(d.name) == 0) {
            // replace the directory at location i (same name)
            directories.set(i, d);
        } else {
            // insert at location i (move all other dirs down one)
            directories.add(i, d);
        }
    }

    @Override
    public Directory get(String name) {
        int i = rank(name);
        if (i < directories.size() && directories.get(i).name.compareTo(name) == 0) {
            // directory at i matches what we are searching for
            return directories.get(i);
        }
        return null;
    }

    @Override
    public Iterator<Directory> iterator() {
        Collections.sort(directories);
        return directories.iterator();
    }

    protected int rank(String name) {
        if (directories.size() == 0) {
            return 0;
        }
        return rank(name, 0, directories.size() - 1);
    }

    protected int rank(String name, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = name.compareTo(directories.get(mid).name);
        if (cmp < 0) {
            // item might be to the left
            return rank(name, lo, mid - 1);
        }
        if (cmp > 0) {
            // item might be to the right
            return rank(name, mid + 1, hi);
        }
        return mid;
    }
}
