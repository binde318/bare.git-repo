package com.binde.stock_project.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class BadRequestException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BadRequestException(String message) {
        this.message = message;
    }

    public BadRequestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
