package com.mlilley.directories.directories;

import java.util.*;

/**
 * MaxPQDirectoryStore
 * Uses an arraylist to store heap ordered items.
 *   - inserts: O(logN) (pq insert)
 *   - searches: O(N) (have to scan)
 *   - ordered enumeration: O(logN) (heap sort, BUT this destroys the heap order and subsequent add and gets are broken)
 */
public class MaxPQDirectoryStore implements DirectoryStore {
    protected List<Directory> directories;

    public MaxPQDirectoryStore() {
        directories = new ArrayList<Directory>();
    }

    @Override
    public void add(Directory d) {
        // add to end and swim up
        directories.add(d);
        swim(directories.size() - 1);
    }

    @Override
    public Directory get(String name) {
        // have to scan - heaps dont support efficient search
        for (Directory d : directories) {
            if (d.name.compareTo(name) == 0) {
                return d;
            }
        }
        return null;
    }

    @Override
    public List<Directory> dirs() {
        // heap sort in place
        int N = directories.size();
        // 1) construct heap by starting at N/2 (leaf nodes dont need processing) and work left, sinking
        for (int i = N/2 - 1; i >= 0; i--) {
            sink(i);
        }
        // 2) exchange last node for largest (puts largest at right), then sink new root node and shrink
        while (N > 1) {
            exchange(0, N-1);
            N--;
            sink(1);
        }
        return directories;
    }

    protected void swim(int i) {
        while (i > 0 && less((i-1)/2, i)) {
            exchange((i-1)/2, i);
            i = (i-1)/2;
        }
    }

    protected void sink(int i) {
        int N = directories.size();
        while (2*i+1 < N) {
            if (2*i+1 == N - 1) {
                if (!less(2*i+1, i)) break;
                exchange(2*i+1, i);
                i = 2*i+1;
            } else {
                if (less(2*i+1, i)) {
                    if (less(2*i+1, 2*i+2)) {
                        exchange(2*i+2, i);
                        i = 2*i+2;
                    } else {
                        exchange(2*i+1, i);
                        i = 2*i+1;
                    }
                } else if (less(2*i+2, i)) {
                    exchange(2*i+1, i);
                    i = 2*i+1;
                } else {
                    break;
                }
            }
        }
    }

    protected boolean less(int i, int j) {
        return directories.get(i).compareTo(directories.get(j)) < 0;
    }

    protected void exchange(int i, int j) {
        Directory tmp = directories.get(i);
        directories.set(i, directories.get(j));
        directories.set(j, tmp);
    }

}
