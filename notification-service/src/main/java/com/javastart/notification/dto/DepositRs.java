package com.javastart.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@Getter
@Setter
@ToString
public class DepositRs {

    private BigDecimal amount;
    private String email;

}
