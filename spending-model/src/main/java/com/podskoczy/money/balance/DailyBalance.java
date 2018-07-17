package com.podskoczy.money.balance;

import com.podskoczy.money.Money;
import com.podskoczy.money.income.Income;

import com.podskoczy.money.expense.Expense;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Michal on 2018-01-13.
 */
public class DailyBalance {


    public Money calculateBalance(LocalDate date, List<Expense> expenses, List<Income> incomes) {

        Map<LocalDate, Money> spendingsByDate = expenses.stream().collect(
                Collectors.groupingBy(Expense::getDate,
                        Collectors.reducing(Money.ZERO_PLN, Expense::getMoney, Money::add)));

        Map<LocalDate, Money> earningsByDate = incomes.stream().collect(
                Collectors.groupingBy(Income::getDate,
                        Collectors.reducing(Money.ZERO_PLN, Income::getMoney, Money::add)));

        List<LocalDate> localDates = Stream.of(spendingsByDate.keySet(), earningsByDate.keySet()).flatMap(Set::stream)
                .distinct().collect(Collectors.toList());

        if (!localDates.contains(date)) {
            localDates.add(date);
        }

        Collections.sort(localDates);

        Money expenseBeforeDate = localDates.stream().filter(d -> !d.isAfter(date))
                .map(d -> Optional.ofNullable(spendingsByDate.get(d)).orElse(Money.ZERO_PLN))
                .reduce(Money::add).orElse(Money.ZERO_PLN);

        Money incomeBeforeDate = localDates.stream().filter(d -> !d.isAfter(date))
                .map(d -> Optional.ofNullable(earningsByDate.get(d)).orElse(Money.ZERO_PLN))
                .reduce(Money::add).orElse(Money.ZERO_PLN);

        return incomeBeforeDate.subtract(expenseBeforeDate);

    }


}
