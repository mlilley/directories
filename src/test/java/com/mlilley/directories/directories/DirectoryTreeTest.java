package com.mlilley.directories.directories;

import com.mlilley.directories.exceptions.CannotMoveUpFromRootDirectoryException;
import com.mlilley.directories.exceptions.DirectoryAlreadyExistsException;
import com.mlilley.directories.exceptions.DirectoryNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class DirectoryTreeTest {

    @Test(expected = CannotMoveUpFromRootDirectoryException.class)
    public void navigateToParentDirectoryFromRootShouldThrow() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.navigateToParentDirectory();
    }

    @Test(expected = DirectoryNotFoundException.class)
    public void navigateToNonExistentSubdirectoryShouldThrow() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.navigateToSubdirectory("nothere");
    }

    @Test
    public void navigateToExistingSubdirectoryShouldSucceed() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
    }

    @Test
    public void pathShouldBeRootWhenInRoot() {
        DirectoryTree dt = new DirectoryTree();
        String path = dt.currentPath();
        assertEquals("root", path);
    }

    @Test
    public void pathShouldBeCorrectAfterNavigatingToSubdir() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        String path = dt.currentPath();
        assertEquals("root\\foo", path);
    }

    @Test
    public void pathShouldBeCorrectAfterNavigatingToMultipleSubdirs() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        dt.createSubdirectory("bar");
        dt.navigateToSubdirectory("bar");
        String path = dt.currentPath();
        assertEquals("root\\foo\\bar", path);
    }

    @Test
    public void canCreateOneSubdir() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("dir1");
        List<String> dirs = dt.subdirectories();
        assertEquals(1, dirs.size());
        assertEquals(true, dirs.contains("dir1"));
    }

    @Test
    public void canCreateMultipleSubdirs() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.createSubdirectory("bar");
        dt.createSubdirectory("baz");
        List<String> dirs = dt.subdirectories();
        assertEquals(3, dirs.size());
        assertEquals(true, dirs.contains("foo"));
        assertEquals(true, dirs.contains("bar"));
        assertEquals(true, dirs.contains("baz"));
    }

    @Test
    public void canCreateSubdirsWhenNotInRoot() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        dt.createSubdirectory("bar");
        dt.createSubdirectory("baz");
        String path = dt.currentPath();
        List<String> dirs = dt.subdirectories();
        assertEquals("root\\foo", path);
        assertEquals(2, dirs.size());
        assertEquals(true, dirs.contains("bar"));
        assertEquals(true, dirs.contains("baz"));
    }

    @Test(expected = DirectoryAlreadyExistsException.class)
    public void creatingExistingSubdirectoryShouldThrow() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.createSubdirectory("foo");
        List<String> dirs = dt.subdirectories();
        assertEquals(1, dirs.size());
        assertEquals(true, dirs.contains("foo"));
    }

    @Test
    public void newTreeShouldHaveNoSubdirs() {
        DirectoryTree dt = new DirectoryTree();
        List<String> dirs = dt.subdirectories();
        assertEquals(true, dirs.isEmpty());
    }

    @Test
    public void canMoveUpDirs() throws Exception {
        DirectoryTree dt = new DirectoryTree();
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        dt.createSubdirectory("bar");
        dt.navigateToSubdirectory("bar");
        dt.createSubdirectory("baz");
        dt.navigateToSubdirectory("baz");
        assertEquals("root\\foo\\bar\\baz", dt.currentPath());
        dt.navigateToParentDirectory();
        assertEquals("root\\foo\\bar", dt.currentPath());
        dt.navigateToParentDirectory();
        assertEquals("root\\foo", dt.currentPath());
        dt.navigateToParentDirectory();
        assertEquals("root", dt.currentPath());
    }

    protected List<String> subdirectoriesAsList(Iterator<String> subdirectoryIterator) {
        List<String> subdirectories = new ArrayList<String>();
        while (subdirectoryIterator.hasNext()) {
            subdirectories.add(subdirectoryIterator.next());
        }
        return subdirectories;
    }
}
