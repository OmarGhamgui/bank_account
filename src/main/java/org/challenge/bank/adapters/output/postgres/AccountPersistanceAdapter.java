package org.challenge.bank.adapters.output.postgres;

import lombok.RequiredArgsConstructor;
import org.challenge.bank.application.port.AccountPersistancePort;
import org.challenge.bank.domain.Transaction;
import org.challenge.bank.infrastructure.exceptions.AccountNotFoundException;
import org.challenge.bank.infrastructure.persistance.postgres.account.AccountEntity;
import org.challenge.bank.infrastructure.persistance.postgres.account.AccountRepository;
import org.challenge.bank.infrastructure.persistance.postgres.transaction.TransactionMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AccountPersistanceAdapter implements AccountPersistancePort {

    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public void updateBalance(long accountId, BigDecimal newBalance) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(accountId);
        accountEntityOptional.ifPresentOrElse(accountEntity -> {
            accountEntity.setBalance(newBalance);
            accountRepository.save(accountEntity);
        }, () -> {
            throw new AccountNotFoundException(String.valueOf(accountId));
        });
    }

    @Override
    public Set<Transaction> getTransactionsHistory(long accountId) {
        AtomicReference<Set<Transaction>> transactions = new AtomicReference<>();
       accountRepository.findById(accountId).ifPresentOrElse((account) -> {
           transactions.set(account.getTransactions().stream().map(transactionMapper::mapToTransaction).collect(Collectors.toSet()));
       }, () -> {throw new AccountNotFoundException(String.valueOf(accountId));});
       return transactions.get();
    }

    @Override
    public BigDecimal getBalance(long accountId) {
            return accountRepository.findById(accountId).
                    orElseThrow( () -> new AccountNotFoundException(String.valueOf(accountId))).getBalance();
    }
}
