package org.challenge.bank.adapters.input.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.challenge.bank.application.port.usecases.AccountUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Validated
public class AccountRestAdapter {
    private final AccountUseCase accountUseCase;

    public AccountRestAdapter(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<?> getBalanceByAccountId(@PathVariable Long accountId){
        return ResponseEntity.ok(accountUseCase.getBalance(accountId));
    }

    @PutMapping("/balance/deposit")
    public ResponseEntity<?> depositMoney(@RequestBody @Valid BalanceRequest balanceRequest){
        return ResponseEntity.ok(accountUseCase.deposit(balanceRequest.accountId(), balanceRequest.transactionAmount()));
    }

    @PutMapping("/balance/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestBody @Valid BalanceRequest balanceRequest){
        return ResponseEntity.ok(accountUseCase.withdraw(balanceRequest.accountId(), balanceRequest.transactionAmount()));
    }

    @GetMapping("/{accountId}/history")
    public ResponseEntity<?> getAccountTransactionsHistory(@PathVariable Long accountId){
        return ResponseEntity.ok(accountUseCase.getTransactionsHistory(accountId));
    }

}
