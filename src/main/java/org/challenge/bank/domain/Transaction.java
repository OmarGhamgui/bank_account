package org.challenge.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private String transactionType;
    private BigDecimal amount;}
