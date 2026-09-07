package com.example.corebank.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND),
    INSUFFICIENT_BALANCE(HttpStatus.UNPROCESSABLE_ENTITY),
    ACCOUNT_LOCKED(HttpStatus.CONFLICT),
    ACCOUNT_NOT_LOCKED(HttpStatus.CONFLICT),
    ACCOUNT_NOT_ACTIVE(HttpStatus.CONFLICT),
    DUPLICATE_IDEMPOTENCY_KEY(HttpStatus.CONFLICT),
    DAILY_LIMIT_EXCEEDED(HttpStatus.UNPROCESSABLE_ENTITY),
    PER_TRANSACTION_LIMIT_EXCEEDED(HttpStatus.UNPROCESSABLE_ENTITY),
    SAME_ACCOUNT_TRANSFER(HttpStatus.BAD_REQUEST),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST),
    CONCURRENT_UPDATE_CONFLICT(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
