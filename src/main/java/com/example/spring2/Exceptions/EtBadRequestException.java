package com.example.spring2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EtBadRequestException extends RuntimeException {
    public EtBadRequestException(String message) {
        super(message);
    }
}
