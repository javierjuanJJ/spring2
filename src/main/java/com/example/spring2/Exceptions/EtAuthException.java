package com.example.spring2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EtAuthException extends Exception {

    public EtAuthException(String invalidEmailFormat) {

    }
}
