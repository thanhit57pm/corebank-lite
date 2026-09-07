package com.example.corebank.core.pagination;

import java.util.List;
import java.util.function.Function;

public record CursorPageResponse<T>(
        List<T> content,
        String nextCursor,
        String prevCursor,
        boolean hasNext
) {

    public static <T> CursorPageResponse<T> of(
            List<T> fetchedWithExtra,
            int limit,
            String prevCursor,
            Function<T, String> cursorExtractor) {

        boolean hasNext = fetchedWithExtra.size() > limit;
        List<T> content = hasNext ? fetchedWithExtra.subList(0, limit) : fetchedWithExtra;
        String nextCursor = hasNext && !content.isEmpty()
                ? cursorExtractor.apply(content.get(content.size() - 1))
                : null;

        return new CursorPageResponse<>(content, nextCursor, prevCursor, hasNext);
    }
}
