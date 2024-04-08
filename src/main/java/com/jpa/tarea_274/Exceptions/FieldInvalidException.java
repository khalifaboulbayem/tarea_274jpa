package com.jpa.tarea_274.Exceptions;

public class FieldInvalidException extends BadRequestException {
    public FieldInvalidException(String msg) {
        super(msg);
    }

}
