package com.jpop.bookservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookServiceBaseException extends RuntimeException{
    public BookServiceBaseException(String error){
        super(error);
    }
}
