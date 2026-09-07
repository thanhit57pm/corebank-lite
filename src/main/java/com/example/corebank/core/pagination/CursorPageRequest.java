package com.example.corebank.core.pagination;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CursorPageRequest(
        String cursor,
        @Min(1) @Max(100) Integer limit
) {
    private static final int DEFAULT_LIMIT = 20;

    public int limitOrDefault() {
        return limit != null ? limit : DEFAULT_LIMIT;
    }
}
