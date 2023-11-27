package com.javastart.bill.dto;

import com.javastart.bill.model.Bill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BillRs {

    private Long billId;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private OffsetDateTime creatingDate;
    private Boolean overDraftEnabled;

    public BillRs(Bill bill) {
        billId = bill.getBillId();
        accountId = bill.getAccountId();
        amount = bill.getAmount();
        isDefault = bill.getIsDefault();
        creatingDate = bill.getCreatingDate();
        overDraftEnabled = bill.getOverDraftEnabled();
    }
}
