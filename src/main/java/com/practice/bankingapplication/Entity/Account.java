package com.practice.bankingapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb040_bank_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    private Double balance;

    private Long phoneNo;

}
