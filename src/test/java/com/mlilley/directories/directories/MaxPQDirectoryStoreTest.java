package com.mlilley.directories.directories;

import org.junit.Test;

public class MaxPQDirectoryStoreTest {

    @Test
    public void buildsHeap() {
        MaxPQDirectoryStore ds = new MaxPQDirectoryStore();
        ds.add(new Directory("a", null));
        ds.add(new Directory("b", null));
        ds.add(new Directory("z", null));
        ds.add(new Directory("y", null));
        showHeap(ds);
        ds.dirs();
        showHeap(ds);

    }

    protected void showHeap(MaxPQDirectoryStore ds) {
        for (Directory d : ds.directories) {
            System.out.print(d.name + " ");
        }
        System.out.println();
    }
}
