package com.example.corebank.modules.account.entity;

import com.example.corebank.core.base.BaseEntity;
import com.example.corebank.security.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@SuperBuilder
public class AccountEntity extends BaseEntity {

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountStatus status;

    @Column(name = "daily_limit", nullable = false, precision = 18, scale = 2)
    private BigDecimal dailyLimit;

    @Column(name = "per_txn_limit", nullable = false, precision = 18, scale = 2)
    private BigDecimal perTxnLimit;

    @Version
    @Column(nullable = false)
    private Long version;
}
