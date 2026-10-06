package com.example.corebank.modules.transaction.mapper;

import com.example.corebank.modules.transaction.dto.response.TransactionResponse;
import com.example.corebank.modules.transaction.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "fromAccountNumber", source = "fromAccount.accountNumber")
    @Mapping(target = "toAccountNumber", source = "toAccount.accountNumber")
    TransactionResponse toResponse(TransactionEntity entity);
}
