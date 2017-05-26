package com.mlilley.directories.directories;


import java.util.Iterator;

public class DirectoryNameIterator implements Iterator<String> {
    protected Iterator<Directory> wrappedIterator;

    public DirectoryNameIterator(Iterator<Directory> iterator) {
        wrappedIterator = iterator;
    }

    public boolean hasNext() {
        return wrappedIterator.hasNext();
    }

    public String next() {
        Directory next = wrappedIterator.next();
        return (next == null ? null : next.name);
    }
}
