package com.podskoczy.money.expense.persistance;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Michal on 2018-02-11.
 */
@Table(name = "incomes")
@Entity
@Getter
@NoArgsConstructor
public class IncomeEntity {

    @Id
    @GeneratedValue
    private String id;

    private Category category;

    private LocalDate date;

    private BigDecimal amount;

    private String currency;

    public IncomeEntity(Category category, LocalDate date, Money money) {
        this.category = category;
        this.date = date;
        this.amount = money.getAmount();
        this.currency = money.getCurrency().getCode();
    }

}
