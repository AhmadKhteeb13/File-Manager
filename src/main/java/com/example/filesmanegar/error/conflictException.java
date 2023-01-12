package com.example.filesmanegar.error;

import org.springframework.http.HttpStatus;

public class conflictException extends ApiBaseException{
    public conflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
