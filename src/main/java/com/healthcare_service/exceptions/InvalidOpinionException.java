package com.healthcare_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOpinionException extends RuntimeException{
    public InvalidOpinionException(String message) {
        super(message);
    }
}
