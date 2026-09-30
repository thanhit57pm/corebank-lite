package com.example.corebank.modules.account.dto.request;

import jakarta.validation.constraints.NotNull;

public record AccountCreateRequest(
        @NotNull Long userId
) {}
