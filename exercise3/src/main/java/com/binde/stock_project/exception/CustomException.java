package com.binde.stock_project.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public CustomException( String message) {
        this.message = message;
    }
    public CustomException( String message,HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
