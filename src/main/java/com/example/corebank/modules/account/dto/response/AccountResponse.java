package com.example.corebank.modules.account.dto.response;

import com.example.corebank.modules.account.entity.AccountStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String accountNumber,
        String accountHolderName,
        BigDecimal balance,
        AccountStatus status,
        BigDecimal dailyLimit,
        BigDecimal perTxnLimit,
        LocalDateTime createdAt
) {}