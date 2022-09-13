package com.example.fooddelivery.model;


import com.example.fooddelivery.enums.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ACCOUNTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends AbstractEntity{

    @OneToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS")
    private AccountStatus status;

    @OneToOne
    private FoodMenu foodMenu;

    public static Account create(final Customer customer, AccountStatus status, FoodMenu foodMenu){
        final Account account = new Account();

        account.customer = customer;
        account.status = status;
        account.foodMenu = foodMenu;

        return account;
    }
}
