package org.challenge.bank.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenge.bank.domain.Transaction;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private Double balance;
    private Collection<Transaction>  transactions;


}
