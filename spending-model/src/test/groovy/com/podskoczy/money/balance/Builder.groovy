package com.podskoczy.money.balance

import com.podskoczy.money.Currency
import com.podskoczy.money.Money
import com.podskoczy.money.income.Income
import com.podskoczy.money.expense.Category

import com.podskoczy.money.expense.Expense

import java.time.LocalDate
import java.time.Month

/**
 * Created by Michal on 2018-01-14.
 */
class Builder {

    int year
    Month month
    Category category

    Builder(int year, Month month, Category category) {
        this.year = year
        this.month = month
        this.category = category
    }

    static Income income(LocalDate localDate, double amount) {
        //FIXME
        Income.of(localDate, Money.of(amount, Currency.PLN), Category.FOOD)
    }

    static Expense expense(LocalDate localDate, double amount) {
        //FIXME
        Expense.of(localDate, Money.of(amount, Currency.PLN), Category.FOOD)
    }

}
