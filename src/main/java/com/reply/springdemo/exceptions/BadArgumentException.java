package com.reply.springdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadArgumentException extends  RuntimeException{
    public BadArgumentException(String message){
        super(message);
    }
}
