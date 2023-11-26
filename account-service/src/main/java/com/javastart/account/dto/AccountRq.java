package com.javastart.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRq {
    private String name;
    private String phone;
    private String email;
    private OffsetDateTime creationDate;
    private List<Long> bills;
}
