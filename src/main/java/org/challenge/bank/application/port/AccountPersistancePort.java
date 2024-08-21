package org.challenge.bank.application.port;

import org.challenge.bank.domain.Transaction;
import org.challenge.bank.infrastructure.persistance.postgres.transaction.TransactionEntity;

import java.math.BigDecimal;
import java.util.Set;

public interface AccountPersistancePort {
    void updateBalance(long accountId, BigDecimal amount);

    Set<Transaction> getTransactionsHistory(long accountId);

    BigDecimal getBalance(long accountId);
}
