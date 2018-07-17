package com.podskoczy.money.expense

import com.podskoczy.money.Currency
import com.podskoczy.money.Money
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker
import com.podskoczy.money.management.RefId

import java.time.LocalDate

class PlannedExpenseBuilder {

    private RefId refId

    PlannedExpenseEvents events

    ExpenseRepository expenses

    ExpenseOverPlanMarker expenseOverPlanMarker

    PlannedExpenseBuilder(RefId refId) {
        this.refId = refId
    }

    PlannedExpenseBuilder(RefId refId, PlannedExpenseEvents events, ExpenseRepository expenses, ExpenseOverPlanMarker expenseOverPlanMarker) {
        this.refId = refId
        this.events = events
        this.expenses = expenses
        this.expenseOverPlanMarker = expenseOverPlanMarker
    }

    PlannedExpense build(double plannedAmount) {
        return new PlannedExpense(refId, LocalDate.MIN, LocalDate.MAX, Category.FOOD, Money.of(plannedAmount, Currency.PLN), Money.ZERO_PLN, expenses, events, expenseOverPlanMarker)
    }


}
