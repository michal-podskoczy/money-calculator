package com.podskoczy.money.expense.persistance;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Category;
import com.podskoczy.money.management.ExpenseId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.podskoczy.money.Currency.PLN;

/**
 * Created by Michal on 2018-02-11.
 */
@Table(name = "expenses")
@Entity
@Getter
@NoArgsConstructor
public class ExpenseEntity {

    @Id
    @GeneratedValue
    private String id;

    private String expenseId;

    private Category category;

    private LocalDate date;

    private BigDecimal amount;

    private String currency;

    public Money getMoney() {
        return Money.of(amount, PLN); //FIXME waluty
    }

    public ExpenseEntity(ExpenseId expenseId, Category category, LocalDate date, Money money) {
        this.expenseId = expenseId.getId();
        this.category = category;
        this.date = date;
        this.amount = money.getAmount();
        this.currency = money.getCurrency().getCode();
    }

}
