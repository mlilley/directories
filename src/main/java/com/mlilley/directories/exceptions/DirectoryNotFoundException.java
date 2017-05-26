package com.mlilley.directories.exceptions;

public class DirectoryNotFoundException extends Exception {
    public DirectoryNotFoundException() {
        super();
    }
    public DirectoryNotFoundException(String message) {
        super(message);
    }
    public DirectoryNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

