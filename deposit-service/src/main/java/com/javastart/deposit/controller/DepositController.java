package com.javastart.deposit.controller;

import com.javastart.deposit.dto.DepositRq;
import com.javastart.deposit.dto.DepositRs;
import com.javastart.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @PostMapping
    public DepositRs deposit(@RequestBody DepositRq depositRq) {
        return depositService.deposit(depositRq.getAccountId(),
                depositRq.getBillId(), depositRq.getAmount());
    }
}
