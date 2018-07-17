package com.podskoczy.money.budget.projection;

import com.podskoczy.money.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "budget_balances")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class BudgetBalanceEntity {

    @Id
    @GeneratedValue
    private String id;

    private String refId;

    private LocalDate date;

    private BigDecimal amount;

    private String currency;

    BudgetBalanceEntity(String refId, LocalDate date, Money balance) {
        this.refId = refId;
        this.date = date;
        this.amount = balance.getAmount();
        this.currency = balance.getCurrency().getCode();
    }

}
