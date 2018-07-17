package com.podskoczy.money.expense

import com.podskoczy.money.Money
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker
import com.podskoczy.money.management.RefId
import spock.lang.Specification

import java.time.LocalDate

import static com.podskoczy.money.Currency.PLN
import static com.podskoczy.money.expense.Category.FOOD

/**
 * Created by Michal on 2018-01-31.
 */
class PlannedExpenseSpec extends Specification {

    RefId REF_ID = new RefId("refId")
    ExpenseRepository expenseRepository
    PlannedExpenseEvents events
    ExpenseOverPlanMarker expenseOverPlanMarker

    def builder() {
        new PlannedExpenseBuilder(REF_ID, events, expenseRepository, expenseOverPlanMarker)
    }

    def setup() {
        expenseRepository = Mock(ExpenseRepository)
        events = Mock(PlannedExpenseEvents)
        expenseOverPlanMarker = Mock(ExpenseOverPlanMarker)
    }

    def "should emit PlannedExpenseOverdrawnEvent when prediction is overdrawn"() {
        given:
        expenseRepository.get(*_) >> [spending(100), spending(240), spending(233.23d)]
        PlannedExpense plannedExpense = builder().build(100)

        when:
        plannedExpense.checkState()

        then:
        1 * events.emit(PlannedExpenseOverdrawnEvent.of(REF_ID, LocalDate.MIN, LocalDate.MAX, FOOD))
    }




    def spending(double amount) {
        Expense.of(LocalDate.now(), Money.of(amount, PLN), FOOD)
    }

}
