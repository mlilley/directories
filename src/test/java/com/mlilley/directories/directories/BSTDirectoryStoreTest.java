package com.mlilley.directories.directories;

import org.junit.Test;

public class BSTDirectoryStoreTest {

    @Test
    public void buildsHeap() {
        BSTDirectoryStore ds = new BSTDirectoryStore();
        ds.add(new Directory("a", null));
        ds.add(new Directory("b", null));
        ds.add(new Directory("z", null));
        ds.add(new Directory("y", null));
        showHeap(ds);
    }

    protected void showHeap(BSTDirectoryStore ds) {
        showHeap(ds.root);
    }

    protected void showHeap(BSTDirectoryStore.Node node) {
        if (node == null) return;
        showHeap(node.left);
        System.out.println(node.directory.name);
        showHeap(node.right);
    }
}
