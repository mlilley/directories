package com.mlilley.directories.exceptions;

public class CannotMoveUpFromRootDirectoryException extends Exception {
    public CannotMoveUpFromRootDirectoryException() {
        super();
    }
    public CannotMoveUpFromRootDirectoryException(String message) {
        super(message);
    }
    public CannotMoveUpFromRootDirectoryException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

