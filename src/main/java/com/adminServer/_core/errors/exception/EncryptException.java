package com.adminServer._core.errors.exception;

import com.adminServer._core.errors.ErrorMessage;

public class EncryptException extends RuntimeException {

    public EncryptException(String message) {
        super(ErrorMessage.ENCRYPT_ERROR + message);
    }
}
