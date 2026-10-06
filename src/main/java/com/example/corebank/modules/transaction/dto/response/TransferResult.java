package com.example.corebank.modules.transaction.dto.response;

/**
 * duplicate = true nghĩa là request có idempotencyKey trùng với giao dịch
 * đã xử lý trước đó — trả về đúng kết quả cũ, KHÔNG tạo giao dịch mới.
 */
public record TransferResult(
        TransactionResponse transaction,
        boolean duplicate
) {}
