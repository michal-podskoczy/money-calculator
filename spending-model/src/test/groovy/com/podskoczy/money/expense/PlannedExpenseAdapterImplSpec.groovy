package com.podskoczy.money.expense

import com.podskoczy.money.expense.adapter.PlannedExpenseSummary
import com.podskoczy.money.management.RefId
import spock.lang.Specification

import static com.podskoczy.money.Currency.PLN
import static com.podskoczy.money.Money.ZERO_PLN
import static com.podskoczy.money.Money.of

class PlannedExpenseAdapterImplSpec extends Specification {

    def ANY_REF_ID = RefId.of("ANY_REF_ID")
    def plannedExpenseRepository = Mock(PlannedExpenseRepository)
    def plannedExpenseAdapter = new PlannedExpenseAdapterImpl(plannedExpenseRepository)


    def "should return planned expense summary as a sum of expenses"(Collection<PlannedExpense> plannedExpenses, PlannedExpenseSummary expectedSum) {
        given:
        plannedExpenseRepository.get(ANY_REF_ID) >> plannedExpenses

        when:
        def plannedExpenseSummary = plannedExpenseAdapter.getPlannedExpenseSummary(ANY_REF_ID)

        then:
        plannedExpenseSummary == expectedSum

        where:
        plannedExpenses                                                          || expectedSum
        [plannedExpense(100.0d), plannedExpense(200.0d), plannedExpense(300.0d)] || summary(600)
    }

    /*def "test getPlannedExpenses"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }*/

    def builder() {
        new PlannedExpenseBuilder(ANY_REF_ID)
    }

    def plannedExpense(double plannedAmount) {
        return builder().build(plannedAmount)
    }

    def summary(double expectedAmount) {
        return new PlannedExpenseSummary(of(expectedAmount, PLN), ZERO_PLN)
    }
}
