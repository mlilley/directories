package com.mlilley.directories.exceptions;

public class DirectoryAlreadyExistsException extends Exception {
    public DirectoryAlreadyExistsException() {
        super();
    }
    public DirectoryAlreadyExistsException(String message) {
        super(message);
    }
    public DirectoryAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

