package com.adminServer._core.errors.exception;

public class EmptyDtoRequestException extends RuntimeException {

    public EmptyDtoRequestException(String message) {
        super(message);
    }
}
