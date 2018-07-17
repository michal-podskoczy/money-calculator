package com.podskoczy.money.savings

import com.podskoczy.money.Currency
import com.podskoczy.money.Money
import spock.lang.Specification

import java.time.LocalDate

import static com.podskoczy.money.balance.Builder.expense
import static com.podskoczy.money.balance.Builder.income

/**
 * Created by Michal on 2018-02-22.
 */
class SavingsCalculatorSpec extends Specification {

    SavingsCalculator savingsCalculator

    Expenses expenses = Mock(Expenses)
    Incomes incomes = Mock(Incomes)

    static LocalDate now = LocalDate.of(2018, 3, 12)

    def setup() {
        savingsCalculator = new SavingsCalculator(expenses, incomes)

        expenses.get() >> [expense(now, 100), expense(now.plusDays(1), 155)]
        incomes.get() >> [income(now.plusDays(2), 150), income(now.plusDays(3), 300)]
    }

    def "should calculate savings with formula earning minus spendings"(LocalDate from, LocalDate to, Money savedMoney) {
        expect:
        savingsCalculator.calculateSavings(from, to) == savedMoney

        where:
        from | to  || savedMoney
        now  | now || Money.of(195, Currency.PLN)
    }
}
