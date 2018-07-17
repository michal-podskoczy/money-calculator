package com.podskoczy.money.expense;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.ExpenseId;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

/**
 * Created by Michal on 2018-01-13.
 */
@Value
@AllArgsConstructor(staticName = "of")
public class Expense {

    private ExpenseId id;

    private LocalDate date;

    private Money money;

    private Category category;

    public static Expense of(LocalDate date, Money money, Category category) {
        return Expense.of(ExpenseId.of(), date, money, category);
    }

}
