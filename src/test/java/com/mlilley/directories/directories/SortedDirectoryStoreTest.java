package com.mlilley.directories.directories;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SortedDirectoryStoreTest {

    @Test
    public void addsItemsAtEnd() {
        SortedDirectoryStore ds = new SortedDirectoryStore();

        ds.add(new Directory("B", null));
        ds.add(new Directory("A", null));
        ds.add(new Directory("C", null));

        assertEquals("A", ds.directories.get(0).name);
        assertEquals("B", ds.directories.get(1).name);
        assertEquals("C", ds.directories.get(2).name);
    }

    @Test
    public void getsItems() {
        SortedDirectoryStore ds = new SortedDirectoryStore();

        ds.add(new Directory("B", null));
        ds.add(new Directory("A", null));
        ds.add(new Directory("C", null));

        assertEquals("A", ds.get("A").name);
        assertEquals("B", ds.get("B").name);
        assertEquals("C", ds.get("C").name);
    }
}
