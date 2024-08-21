package org.challenge.bank.adapters.output.postgres;

import org.challenge.bank.domain.Transaction;
import org.challenge.bank.infrastructure.exceptions.AccountNotFoundException;
import org.challenge.bank.infrastructure.persistance.postgres.account.AccountEntity;
import org.challenge.bank.infrastructure.persistance.postgres.account.AccountRepository;
import org.challenge.bank.infrastructure.persistance.postgres.transaction.TransactionEntity;
import org.challenge.bank.infrastructure.persistance.postgres.transaction.TransactionMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountPersistanceAdapterTest {

    private AccountPersistanceAdapter underTest;
    private AutoCloseable autoCloseable;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionMapper transactionMapper;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AccountPersistanceAdapter(accountRepository, transactionMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void updateBalance_Success() {
        // Arrange
        long accountId = 1;
        Set<TransactionEntity> emptySet = Collections.emptySet();
        BigDecimal newBalance = new BigDecimal(2000);
        AccountEntity accountEntity = new AccountEntity(10l, new BigDecimal(1000), emptySet);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(accountEntity));

        // Act
        underTest.updateBalance(accountId, newBalance);

        // Assert
        assertEquals(newBalance, accountEntity.getBalance());
        verify(accountRepository).save(accountEntity);
    }

    @Test
    public void updateBalance_AccountNotFound() {
        // Arrange
        long accountId = 2L;
        BigDecimal newBalance = BigDecimal.valueOf(1500.00);
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> {
            underTest.updateBalance(accountId, newBalance);
        });
    }

    @Test
    void getTransactionsHistory_Success() {
        TransactionEntity transactionEntity1 = new TransactionEntity();
        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity1.setId(1l);
        transactionEntity2.setId(2l);
        Set<TransactionEntity> transactionEntities = new HashSet<>();
        transactionEntities.add(transactionEntity1);
        transactionEntities.add(transactionEntity2);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setTransactions(transactionEntities);
        long accountId = 1L;
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        transaction1.setId(1l);
        transaction2.setId(2l);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(accountEntity));
        when(transactionMapper.mapToTransaction(transactionEntity1)).thenReturn(transaction1);
        when(transactionMapper.mapToTransaction(transactionEntity2)).thenReturn(transaction2);

        // Act
        Set<Transaction> transactions = underTest.getTransactionsHistory(accountId);

        // Assert
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertTrue(transactions.contains(transaction1));
        assertTrue(transactions.contains(transaction2));
    }

    @Test
    public void testGetTransactionsHistory_AccountNotFound() {
        // Arrange
        long accountId = 2L;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> {
            underTest.getTransactionsHistory(accountId);
        });
    }

    @Test
    void getBalance_Success() {
        // Arrange
        long accountId = 1;
        Set<TransactionEntity> emptySet = Collections.emptySet();
        BigDecimal newBalance = new BigDecimal(2000);
        AccountEntity accountEntity = new AccountEntity(10l, new BigDecimal(1000), emptySet);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(accountEntity));

        // Test
        assertEquals(new BigDecimal(1000), underTest.getBalance(accountId));
    }

}