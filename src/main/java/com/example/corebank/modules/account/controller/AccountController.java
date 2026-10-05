package com.example.corebank.modules.account.controller;

import com.example.corebank.common.api.ApiResponse;
import com.example.corebank.modules.account.dto.request.AccountCreateRequest;
import com.example.corebank.modules.account.dto.request.AccountLockRequest;
import com.example.corebank.modules.account.dto.response.AccountResponse;
import com.example.corebank.modules.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo tài khoản thành công", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccount(@PathVariable Long id) {
        AccountResponse response = accountService.getAccountResponse(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // Bài 45: chỉ TELLER hoặc ADMIN mới được khóa/mở khóa tài khoản
    // TODO: Add @PreAuthorize after JWT/Security configuration
    @PatchMapping("/{id}/lock")
    public ResponseEntity<ApiResponse<Void>> lockAccount(
            @PathVariable Long id, @Valid @RequestBody AccountLockRequest request) {
        accountService.lockAccount(id, request);
        return ResponseEntity.ok(ApiResponse.success("Đã khóa tài khoản", null));
    }

    // TODO: Add @PreAuthorize after JWT/Security configuration
    @PatchMapping("/{id}/unlock")
    public ResponseEntity<ApiResponse<Void>> unlockAccount(@PathVariable Long id) {
        accountService.unlockAccount(id);
        return ResponseEntity.ok(ApiResponse.success("Đã mở khóa tài khoản", null));
    }
}
