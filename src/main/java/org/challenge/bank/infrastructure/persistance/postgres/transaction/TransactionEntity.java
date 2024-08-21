package org.challenge.bank.infrastructure.persistance.postgres.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenge.bank.infrastructure.persistance.postgres.account.AccountEntity;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Entity(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transactionType;

    private BigDecimal amount;

    @ManyToOne
    private AccountEntity account;
}
