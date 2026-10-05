package com.example.corebank.modules.account.service;

import com.example.corebank.core.base.BaseService;
import com.example.corebank.modules.account.dto.request.AccountCreateRequest;
import com.example.corebank.modules.account.dto.request.AccountLockRequest;
import com.example.corebank.modules.account.dto.response.AccountResponse;
import com.example.corebank.modules.account.entity.AccountEntity;

public interface AccountService extends BaseService<AccountEntity, Long> {

    AccountResponse createAccount(AccountCreateRequest request);

    AccountResponse getAccountResponse(Long id);

    void lockAccount(Long id, AccountLockRequest request);

    void unlockAccount(Long id);
}
