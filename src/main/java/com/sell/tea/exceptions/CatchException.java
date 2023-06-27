package com.sell.tea.exceptions;

import org.springframework.web.ErrorResponse;

public class CatchException extends RuntimeException {
    public CatchException(String message) {
        super(message);
    }

}
