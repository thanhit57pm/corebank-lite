package com.example.corebank.modules.transaction.dto.response;

import com.example.corebank.modules.transaction.entity.TransactionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        String idempotencyKey,
        String fromAccountNumber,
        String toAccountNumber,
        BigDecimal amount,
        TransactionStatus status,
        String failureReason,
        LocalDateTime createdAt,
        LocalDateTime completedAt
) {}
