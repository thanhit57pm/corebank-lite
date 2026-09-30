package com.example.corebank.modules.account.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccountLockRequest(
    @NotNull String reason
) {}
