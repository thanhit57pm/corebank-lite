package com.example.corebank.common.utils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

public final class CursorUtils {

    private static final String DELIMITER = "_";

    private CursorUtils() {
    }

    public static String encode(LocalDateTime createdAt, Long id) {
        String raw = createdAt.toString() + DELIMITER + id;
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    public static CursorPayload decode(String cursor) {
        if (cursor == null || cursor.isBlank()) {
            return null;
        }
        String raw = new String(Base64.getUrlDecoder().decode(cursor), StandardCharsets.UTF_8);
        String[] parts = raw.split(DELIMITER, 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Cursor không hợp lệ");
        }
        return new CursorPayload(LocalDateTime.parse(parts[0]), Long.parseLong(parts[1]));
    }

    public record CursorPayload(LocalDateTime createdAt, Long id) {}
}
