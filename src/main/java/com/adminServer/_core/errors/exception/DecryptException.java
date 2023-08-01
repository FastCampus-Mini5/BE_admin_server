package com.adminServer._core.errors.exception;

import com.adminServer._core.errors.ErrorMessage;

public class DecryptException extends RuntimeException {

    public DecryptException(String message) {
        super(ErrorMessage.DECRYPT_ERROR + message);
    }
}
