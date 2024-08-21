package org.challenge.bank.application.port.usecases;

import org.challenge.bank.domain.Transaction;

import java.math.BigDecimal;
import java.util.Set;

public interface AccountUseCase {
     boolean deposit(long accountId, BigDecimal amount);

     boolean withdraw(long accountId, BigDecimal amount);

     Set<Transaction> getTransactionsHistory(long accountId);

     BigDecimal getBalance(long accountId);
}
