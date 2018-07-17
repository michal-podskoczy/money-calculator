package com.podskoczy.money.expense.persistance;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.ExpenseId;
import com.podskoczy.money.management.RefId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "expenses")
@Entity
@Getter
@NoArgsConstructor
public class ExpenseOverPlanEntity {

    @Id
    @GeneratedValue
    private String id;

    private String expenseId;

    private String refId;

    private BigDecimal amount;

    private String currency;

    public ExpenseOverPlanEntity(ExpenseId expenseId, RefId refId, Money amount) {
        this.expenseId = expenseId.getId();
        this.refId = refId.getRefId();
        this.amount = amount.getAmount();
        this.currency = amount.getCurrency().toString();
    }

}
