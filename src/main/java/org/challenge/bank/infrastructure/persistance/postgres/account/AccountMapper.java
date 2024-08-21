package org.challenge.bank.infrastructure.persistance.postgres.account;

import org.challenge.bank.domain.account.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface AccountMapper {
    Account mapToAccount(AccountEntity accountEntity);
}
