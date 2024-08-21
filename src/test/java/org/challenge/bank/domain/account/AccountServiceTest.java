package org.challenge.bank.domain.account;

import org.challenge.bank.adapters.output.postgres.AccountPersistanceAdapter;
import org.challenge.bank.application.port.AccountPersistancePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountService underTest;
    private AutoCloseable autoCloseable;

    @Mock
    private AccountPersistancePort accountPersistancePort;

    @BeforeEach
    void setUp() {
        underTest = new AccountService(accountPersistancePort);
    }



    @Test
    void deposit() {
        long accountId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        BigDecimal currentBalance = BigDecimal.valueOf(1000.00);
        BigDecimal newBalance = currentBalance.add(amount);

        when(underTest.getBalance(accountId)).thenReturn(currentBalance);

        boolean result = underTest.deposit(accountId, amount);

        assertTrue(result);
        verify(accountPersistancePort).updateBalance(accountId, newBalance);
    }

    @Test
    void withdraw() {
        long accountId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        BigDecimal currentBalance = BigDecimal.valueOf(1000.00);
        BigDecimal newBalance = currentBalance.subtract(amount);

        when(underTest.getBalance(accountId)).thenReturn(currentBalance);

        boolean result = underTest.withdraw(accountId, amount);

        assertTrue(result);
        verify(accountPersistancePort).updateBalance(accountId, newBalance);
    }

    @Test
    void getTransactionsHistory() {
        long accountId = 1L;
        // When
        underTest.getTransactionsHistory(accountId);
        // Then
        verify(accountPersistancePort).getTransactionsHistory(accountId);
    }

    @Test
    void getBalance() {
        long accountId = 1L;
        // When
        underTest.getBalance(accountId);
        // Then
        verify(accountPersistancePort).getBalance(accountId);
    }
}