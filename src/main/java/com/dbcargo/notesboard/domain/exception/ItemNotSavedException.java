package com.dbcargo.notesboard.domain.exception;

public class ItemNotSavedException extends RuntimeException {

    public ItemNotSavedException() {
        super();
    }

    public ItemNotSavedException(String message) {
        super(message);
    }
}
