package com.binde.stock_project.utils;

import com.binde.stock_project.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ApiUtil {
    public static <T> ResponseEntity<ApiResponse<T>> errorResponse(HttpStatus status, String errMsg){
        ApiResponse <T> ar = new ApiResponse<>(status);
        ar.setMessage(errMsg);
        ar.setStatus(true);
        ar.getTime();
        return new ResponseEntity<>(ar,status);
    }
}
