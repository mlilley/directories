package com.mlilley.directories.directories;

import com.mlilley.directories.exceptions.CannotMoveUpFromRootDirectoryException;
import com.mlilley.directories.exceptions.DirectoryAlreadyExistsException;
import com.mlilley.directories.exceptions.DirectoryNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DirectoryTree {
    public static final String SEPARATOR = "\\";
    protected Directory root;
    protected Directory current;

    public DirectoryTree() {
        root = new Directory("root", null);
        current = root;
    }

    public void createSubdirectory(String name) throws DirectoryAlreadyExistsException {
        Directory subdir = current.subdirectories.get(name);
        if (subdir != null) {
            throw new DirectoryAlreadyExistsException();
        }
        subdir = new Directory(name, current);
        current.subdirectories.add(subdir);
    }

    public void navigateToParentDirectory() throws CannotMoveUpFromRootDirectoryException {
        if (current.parent == null) {
            throw new CannotMoveUpFromRootDirectoryException();
        }
        current = current.parent;
    }

    public void navigateToSubdirectory(String name) throws DirectoryNotFoundException {
        Directory subdir = current.subdirectories.get(name);
        if (subdir == null) {
            throw new DirectoryNotFoundException();
        }
        current = subdir;
    }

    public String currentPath() {
        List<String> dirs = new ArrayList<String>();
        Directory dir = current;
        while (dir != null) {
            dirs.add(0, dir.name);
            dir = dir.parent;
        }
        return String.join(SEPARATOR, dirs);
    }

    public List<String> subdirectories() {
        List<String> names = new ArrayList<String>();
        for (Directory dir : current.subdirectories.dirs()) {
            names.add(dir.name);
        }
        return names;
    }
}
