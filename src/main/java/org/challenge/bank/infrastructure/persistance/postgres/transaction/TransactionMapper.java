package org.challenge.bank.infrastructure.persistance.postgres.transaction;

import org.challenge.bank.domain.Transaction;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public abstract class TransactionMapper {
    public abstract Transaction mapToTransaction(TransactionEntity transactionEntity);
}
