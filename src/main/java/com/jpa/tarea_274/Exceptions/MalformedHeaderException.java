package com.jpa.tarea_274.Exceptions;

public class MalformedHeaderException extends BadRequestException {
    public MalformedHeaderException(String msg) {
        super(msg);
    }
}