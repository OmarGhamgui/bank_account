package org.challenge.bank.infrastructure.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String accountId){
        super("Bank account not found with ID: " + accountId);
    }
}
