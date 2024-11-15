package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BorrowedBookNotFoundException extends RuntimeException{
        public BorrowedBookNotFoundException(String message){
            super(message);
        }
}
