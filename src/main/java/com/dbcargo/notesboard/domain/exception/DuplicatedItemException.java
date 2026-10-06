package com.dbcargo.notesboard.domain.exception;

public class DuplicatedItemException extends RuntimeException {

    public DuplicatedItemException(String message) {
        super(message);
    }
}
