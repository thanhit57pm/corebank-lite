package com.example.corebank.common.api;

public enum ApiStatus {
    SUCCESS(200),
    VALIDATION_ERROR(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    CONFLICT(409),
    NOT_FOUND(404),
    UNPROCESSABLE_ENTITY(422),
    INTERNAL_ERROR(500);

    private final int code;

    ApiStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
