package com.javastart.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;
    private String name;
    private String phone;
    private String email;
    private OffsetDateTime creationDate;

    @ElementCollection
    private List<Long> bills;

    public Account(String name, String phone, String email, OffsetDateTime creationDate, List<Long> bills) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.creationDate = creationDate;
        this.bills = bills;
    }
}
