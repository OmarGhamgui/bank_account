package org.challenge.bank.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.challenge.bank.application.port.AccountPersistancePort;
import org.challenge.bank.application.port.usecases.AccountUseCase;
import org.challenge.bank.domain.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;


@RequiredArgsConstructor
@Slf4j
@Service
public class AccountService implements AccountUseCase {

    private final AccountPersistancePort accountPersistancePort;

    public boolean deposit(long accountId, BigDecimal amount) {
        BigDecimal newBalance = accountPersistancePort.getBalance(accountId).add(amount);
        try {
            accountPersistancePort.updateBalance(accountId, newBalance);
            return true;
        } catch (RuntimeException e){
            log.error("Something wrong here" + e.getMessage());
        }
        return false;
    }

    public boolean withdraw(long accountId, BigDecimal amount) {
        BigDecimal newBalance = accountPersistancePort.getBalance(accountId).subtract(amount);
        try {
            accountPersistancePort.updateBalance(accountId, newBalance);
            return true;
        } catch (RuntimeException e) {
            log.error("Something wrong here" + e.getMessage());
        }
        return false;
    }

    public Set<Transaction> getTransactionsHistory(long accountId) {
        return accountPersistancePort.getTransactionsHistory(accountId);
    }

    public BigDecimal getBalance(long accountId) {
        return accountPersistancePort.getBalance(accountId);
    }
}
