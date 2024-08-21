package org.challenge.bank.infrastructure.persistance.postgres.account;

import jakarta.persistence.*;
import lombok.*;
import org.challenge.bank.infrastructure.persistance.postgres.transaction.TransactionEntity;

import java.math.BigDecimal;
import java.util.Set;


@Entity(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal balance;

    @OneToMany
    private Set<TransactionEntity> transactions;
}
