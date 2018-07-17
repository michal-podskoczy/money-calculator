package com.podskoczy.money.savings;

import com.podskoczy.money.Money;
import com.podskoczy.money.income.Income;
import com.podskoczy.money.expense.Expense;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Created by Michal on 2018-02-22.
 */
@AllArgsConstructor
public class SavingsCalculator {

    Expenses expenses;
    Incomes incomes;

    public Money calculateSavings(LocalDate from, LocalDate to) {
        Money spendingSum = expenses.get().stream()
                .map(Expense::getMoney)
                .reduce(Money.ZERO_PLN, Money::add);

        Money earningSum = incomes.get().stream()
                .map(Income::getMoney)
                .reduce(Money.ZERO_PLN, Money::add);

        return earningSum.subtract(spendingSum);
    }

    public Money calculateSavings(Year year, Month month) {
        throw new RuntimeException("Not implemented yet.");
    }


}
