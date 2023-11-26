package com.javastart.account.service;

import com.javastart.account.dto.AccountRq;
import com.javastart.account.exception.AccountNotFountException;
import com.javastart.account.model.Account;
import com.javastart.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFountException("Unable to find account with id: " + accountId));
    }

    public Long createAccount(AccountRq accountRq) {
        Account account = new Account(accountRq.getName(),
                accountRq.getEmail(), accountRq.getPhone(),
                OffsetDateTime.now(), accountRq.getBills());
        return accountRepository.saveAndFlush(account).getAccountId();
    }

    public Account updateAccount(Long accountId, String name, String email, String phone, List<Long> bills) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setBills(bills);
        return accountRepository.saveAndFlush(account);
    }

    public Account deleteAccount(Long accountId) {
        Account accountDelete = getAccountById(accountId);
        accountRepository.deleteById(accountId);
        return accountDelete;
    }

}
