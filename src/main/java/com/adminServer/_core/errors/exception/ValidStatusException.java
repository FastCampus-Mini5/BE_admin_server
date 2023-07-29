package com.adminServer._core.errors.exception;

import com.adminServer._core.errors.ErrorMessage;

public class ValidStatusException extends RuntimeException {

    public ValidStatusException() {
        super(ErrorMessage.INVALID_STATUS);
    }
}
