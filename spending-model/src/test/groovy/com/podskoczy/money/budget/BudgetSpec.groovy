package com.podskoczy.money.budget

import com.podskoczy.money.Currency
import com.podskoczy.money.expense.Category
import com.podskoczy.money.expense.adapter.PlannedExpense
import spock.lang.Specification

import static com.podskoczy.money.Money.ZERO_PLN
import static com.podskoczy.money.Money.of
import static com.podskoczy.money.budget.PlannedExpenses.*

class BudgetSpec extends Specification {

    def budgetEvents = Mock(BudgetEvents)
    def expenses = Mock(PlannedExpenses)
    def incomes = Mock(PlannedIncomes)


    def setup() {
        expenses.getPlannedExpenses(_) >> [new PlannedExpense(Category.FOOD, of(140, Currency.PLN), ZERO_PLN)]
        incomes.getPlannedIncomesSum(_) >> new PlannedIncomes.PlannedIncome(ZERO_PLN, ZERO_PLN)
    }

    def "should emit event when balance changes"() {
        given:
        Budget budget = builder().balance(ZERO_PLN).build()

        when:
        budget.updateBalance()

        then:
        1 * budgetEvents.emit(_ as BudgetBalanceChangeEvent)
    }

    def "should not emit event when balance doesn't change"() {
        given:
        Budget budget = builder().balance(of(-140, Currency.PLN)).build()

        when:
        budget.updateBalance()

        then:
        0 * budgetEvents.emit(_ as BudgetBalanceChangeEvent)
    }

    def builder() {
        new BudgetBuilder(budgetEvents, expenses, incomes)
    }


}
