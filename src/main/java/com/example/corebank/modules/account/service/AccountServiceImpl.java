package com.example.corebank.modules.account.service;

import com.example.corebank.common.exception.BusinessException;
import com.example.corebank.common.exception.ErrorCode;
import com.example.corebank.core.base.BaseServiceImpl;
import com.example.corebank.modules.account.dto.request.AccountCreateRequest;
import com.example.corebank.modules.account.dto.request.AccountLockRequest;
import com.example.corebank.modules.account.dto.response.AccountResponse;
import com.example.corebank.modules.account.entity.AccountEntity;
import com.example.corebank.modules.account.entity.AccountStatus;
import com.example.corebank.modules.account.mapper.AccountMapper;
import com.example.corebank.modules.account.repository.AccountRepository;
import com.example.corebank.security.user.UserEntity;
import com.example.corebank.security.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl<AccountEntity, Long> implements AccountService {


    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int MAX_GENERATE_RETRY = 5;

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository,UserRepository userRepository,AccountMapper accountMapper) {
        super(accountRepository, "Account");
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }
    @Override
    public AccountResponse createAccount(AccountCreateRequest request) {
        UserEntity user = userRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, "User khong ton tai voi id: " + request.userId()));
        AccountEntity account = AccountEntity.builder()
                .accountNumber(generateUniqueAccountNumber())
                .user(user)
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .dailyLimit(new BigDecimal("1000000000"))
                .perTxnLimit(new BigDecimal("500000000"))
                .version(0L)
                .build();
        AccountEntity saved = accountRepository.save(account);
        // TODO: bo sung audit log.
        return accountMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccountResponse(Long id) {
        AccountEntity account = findById(id);
        return accountMapper.toResponse(account);
    }

    @Override
    public void lockAccount(Long id, AccountLockRequest request) {
        AccountEntity entry = findById(id);
        entry.setStatus(AccountStatus.LOCKED);
        accountRepository.save(entry);
        //Todo: Log
    }

    @Override
    public void unlockAccount(Long id) {
        AccountEntity account = findById(id);
        if(!account.getStatus().equals(AccountStatus.LOCKED)){
            throw new BusinessException(ErrorCode.ACCOUNT_NOT_LOCKED, "Tài khoản chưa bị khóa: \" + account.getAccountNumber()");
        }
        account.setStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
        //Todo: Log
    }

    private String generateUniqueAccountNumber() {
        for (int attempt = 0; attempt < MAX_GENERATE_RETRY; attempt++) {
            String candidate = "ACC" + String.format("%010d", RANDOM.nextInt(1_000_000_000));
            if (accountRepository.findByAccountNumber(candidate).isEmpty()) {
                return candidate;
            }
        }
        throw new IllegalStateException("Không thể sinh số tài khoản duy nhất sau nhiều lần thử");
    }
}
