package com.jpa.tarea_274.Exceptions;

public class FieldAlreadyExistException extends ConflictException {
    public FieldAlreadyExistException(String msg) {
        super(msg);
    }
}
