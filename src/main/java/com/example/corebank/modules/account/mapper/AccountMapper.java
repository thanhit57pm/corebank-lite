package com.example.corebank.modules.account.mapper;

import com.example.corebank.modules.account.dto.response.AccountResponse;
import com.example.corebank.modules.account.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountHolderName", source = "user.fullName")
    AccountResponse toResponse(AccountEntity entity);
}
