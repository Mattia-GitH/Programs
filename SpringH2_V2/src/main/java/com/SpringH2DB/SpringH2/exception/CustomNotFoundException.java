package com.SpringH2DB.SpringH2.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomNotFoundException(String msg) {
        super(msg);
    }

    public CustomNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
