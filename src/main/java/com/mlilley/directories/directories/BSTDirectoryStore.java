package com.mlilley.directories.directories;


import java.util.ArrayList;
import java.util.List;

/**
 * BSTDirectoryStore
 * Uses a bst to store directories in order, giving fast insert, search, and inorder enumeration
 *   - inserts: O(N to logN)  N in worst case when unbalanced (depends on data)
 *   - searches: O(N to logN)  N in worst case when unbalanced (depends on data)
 *   - ordered enumeration: O(N)
 */

public class BSTDirectoryStore implements DirectoryStore {
    protected Node root;

    protected class Node {
        public Directory directory;
        public Node parent;
        public Node left;
        public Node right;

        public Node(Directory d) {
            this.directory = d;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    public BSTDirectoryStore() {
        root = null;
    }

    @Override
    public void add(Directory d) {
        root = add(root, d);
    }

    protected Node add(Node current, Directory d) {
        if (current == null) return new Node(d);
        int cmp = d.compareTo(current.directory);
        if (cmp < 0) current.left = add(current.left, d);
        if (cmp > 0) current.right = add(current.right, d);
        else if (cmp == 0) current.directory = d;
        return current;
    }

    @Override
    public Directory get(String name) {
        Node result = get(root, name);
        if (result == null) return null;
        return result.directory;
    }

    protected Node get(Node current, String name) {
        if (current == null) return null;
        int cmp = name.compareTo(current.directory.name);
        if (cmp < 0) return get(current.left, name);
        if (cmp > 0) return get(current.right, name);
        else return current;
    }

    @Override
    public List<Directory> dirs() {
        List<Directory> result = new ArrayList<Directory>();
        inOrder(result, root);
        return result;
    }

    protected void inOrder(List<Directory> result, Node current) {
        if (current == null) return;
        inOrder(result, current.left);
        result.add(current.directory);
        inOrder(result, current.right);
    }
}
