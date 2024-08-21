package org.challenge.bank.adapters.input.web;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BalanceRequest(
        long accountId,
        @NotNull
        BigDecimal transactionAmount) {
}
