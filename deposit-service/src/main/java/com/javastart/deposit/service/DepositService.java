package com.javastart.deposit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.deposit.dto.DepositRs;
import com.javastart.deposit.exception.DepositServiceException;
import com.javastart.deposit.model.Deposit;
import com.javastart.deposit.repository.DepositRepository;
import com.javastart.deposit.rest.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class DepositService {
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository depositRepository;
    private final AccountServiceClient accountServiceClient;
    private final BillServiceClient billServiceClient;
    private final RabbitTemplate rabbitTemplate;


    public DepositRs deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account and bill is null");
        }

        if (billId != null) {
            BillRs billById = billServiceClient.getBillById(billId);
            BillRq billRq = createBillRq(billById, amount);

            billServiceClient.update(billId, billRq);

            AccountRs accountById = accountServiceClient.getAccountById(billById.getAccountId());
            depositRepository.saveAndFlush(new Deposit(amount, billId, OffsetDateTime.now(), accountById.getEmail()));

            return getDepositRs(amount, accountById);
        } else {
            BillRs defaultBill = getDefaultBill(accountId);
            BillRq billRq = createBillRq(defaultBill, amount);

            billServiceClient.update(defaultBill.getBillId(), billRq);

            AccountRs accountById = accountServiceClient.getAccountById(accountId);

            depositRepository.saveAndFlush(new Deposit(amount, defaultBill.getBillId(),
                    OffsetDateTime.now(), accountById.getEmail()));

            return getDepositRs(amount, accountById);
        }
    }

    private DepositRs getDepositRs(BigDecimal amount, AccountRs accountById) {
        DepositRs depositRs = new DepositRs(amount, accountById.getEmail());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT,
                    objectMapper.writeValueAsString(depositRs));
        } catch (JsonProcessingException e) {
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositRs;
    }

    private BillRs getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId)
                .stream()
                .filter(BillRs::getIsDefault)
                .findAny()
                .orElseThrow(() -> new DepositServiceException("Unable to find default bill for account: " + accountId));
    }

    private BillRq createBillRq(BillRs billById, BigDecimal amount) {
        BillRq billRq = new BillRq();
        billRq.setAccountId(billById.getAccountId());
        billRq.setCreatingDate(billById.getCreatingDate());
        billRq.setIsDefault(billById.getIsDefault());
        billRq.setOverDraftEnabled(billById.getOverDraftEnabled());
        billRq.setAmount(billById.getAmount().add(amount));
        return billRq;
    }
}
