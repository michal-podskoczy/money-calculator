package com.podskoczy.money.balance

import com.podskoczy.money.Currency
import com.podskoczy.money.Money
import com.podskoczy.money.income.Income
import com.podskoczy.money.expense.Category
import com.podskoczy.money.expense.Expense
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

/**
 * Created by Michal on 2018-01-13.
 */
class DailyBalanceSpec extends Specification {

    def builder = new Builder(2018, Month.JANUARY, Category.FOOD)

    //10 -100 | -100
    //11 +300 | +200
    //12 -150 | +50

    def "should calculate balance for given date"() {
        given:
        DailyBalance balanceCalculator = new DailyBalance()
        LocalDate date = LocalDate.of(2018, 9, 11)

        List<Expense> spendings = [builder.expense(date.minusDays(1), 100),
                                   builder.expense(date.plusDays(1), 150)]

        List<Income> earnings = [builder.income(date, 300)]

        when:
        Money money = balanceCalculator.calculateBalance(date, spendings, earnings)

        then:
        money == Money.of(200, Currency.PLN)
        //money == Money.ZERO_PLN
    }

}
