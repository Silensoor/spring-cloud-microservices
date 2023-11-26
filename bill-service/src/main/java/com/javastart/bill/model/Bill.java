package com.javastart.bill.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long billId;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private OffsetDateTime creatingDate;
    private Boolean overDraftEnabled;

    public Bill(Long accountId, BigDecimal amount,
                Boolean isDefault, OffsetDateTime creatingDate,
                Boolean overDraftEnabled) {
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.creatingDate = creatingDate;
        this.overDraftEnabled = overDraftEnabled;
    }

    public Bill(Long billId, Long accountId,
                BigDecimal amount, Boolean isDefault,
                Boolean overDraftEnabled) {
        this.billId = billId;
        this.accountId = accountId;
        this.amount = amount;
        this.isDefault = isDefault;
        this.overDraftEnabled = overDraftEnabled;
    }
}
