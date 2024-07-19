package com.binde.stock_project.exception;

import com.binde.stock_project.dto.response.ApiResponse;
import com.binde.stock_project.utils.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> userNotFound(NotFoundException exception){
        return ApiUtil.errorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> customException(CustomException exception){
        return ApiUtil.errorResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> badRequestExpection(BadRequestException exception) {
        return ApiUtil.errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
