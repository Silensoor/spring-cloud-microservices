package com.javastart.account.controller;

import com.javastart.account.dto.AccountRq;
import com.javastart.account.dto.AccountRs;
import com.javastart.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public AccountRs getAccount(@PathVariable Long accountId) {
        return new AccountRs(accountService.getAccountById(accountId));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRq accountRq) {
        return accountService.createAccount(accountRq);
    }

    @PutMapping("/{accountId}")
    public AccountRs updateAccount(@PathVariable Long accountId, @RequestBody AccountRq accountRq) {
        return new AccountRs(accountService.updateAccount(accountId,
                accountRq.getName(), accountRq.getEmail(),
                accountRq.getPhone(), accountRq.getBills()));
    }

    @DeleteMapping("/{accountId}")
    public AccountRs deleteAccount(@PathVariable Long accountId) {
        return new AccountRs(accountService.deleteAccount(accountId));
    }
}

