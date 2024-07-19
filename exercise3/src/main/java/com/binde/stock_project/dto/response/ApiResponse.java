package com.binde.stock_project.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private HttpStatus statusCode;
    private boolean status;
    private final LocalDateTime time = LocalDateTime.now();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponse(HttpStatus status) {
        this.statusCode = statusCode;
    }
    public static  <T> ApiResponse<T> buildSuccessTxn(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.OK);
        response.setMessage("Stock updated successful");
        response.setStatus(true);
        response.setData(data);
        return response;
    }

    public static  <T> ApiResponse<T> buildSuccess(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatusCode(HttpStatus.CREATED);
        response.setMessage("Stock created successfully");
        response.setStatus(true);
        response.setData(data);
        return response;
    }

    public static ApiResponse<Void> build() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Success");
        return response;
    }
}
