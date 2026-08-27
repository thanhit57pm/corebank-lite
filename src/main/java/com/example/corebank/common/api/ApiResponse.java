package com.example.corebank.common.api;

import com.example.corebank.common.exception.ErrorDetail;

import java.time.LocalDateTime;
import java.util.List;

public record ApiResponse<T>(
        ApiStatus status,
        String message,
        T data,
        List<ErrorDetail> errors,
        LocalDateTime timestamp
){
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiStatus.SUCCESS, "OK", data, null, LocalDateTime.now());
    }
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ApiStatus.SUCCESS, message, data, null, LocalDateTime.now());
    }
    public static <T> ApiResponse<T> error(ApiStatus status, String message) {
        return new ApiResponse<>(status, message, null, null, LocalDateTime.now());
    }
    public static <T> ApiResponse<T> validationError(List<ErrorDetail> errors) {
        return new ApiResponse<>(ApiStatus.VALIDATION_ERROR,"Dữ liệu không hợp lệ.", null, errors, LocalDateTime.now());
    }
}
