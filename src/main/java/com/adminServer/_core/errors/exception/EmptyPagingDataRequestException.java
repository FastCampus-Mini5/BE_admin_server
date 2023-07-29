package com.adminServer._core.errors.exception;

import com.adminServer._core.errors.ErrorMessage;

public class EmptyPagingDataRequestException extends RuntimeException {

    public EmptyPagingDataRequestException() {
        super(ErrorMessage.EMPTY_DATA_TO_PAGING);
    }
}
