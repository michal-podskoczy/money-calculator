package com.podskoczy.money.budget

import com.podskoczy.money.Money
import com.podskoczy.money.management.RefId

import java.time.LocalDate

class BudgetBuilder {

    public static RefId REF_ID = new RefId("REF_ID")

    private BudgetEvents budgetEvents
    private PlannedExpenses expenses
    private PlannedIncomes incomes

    private Money balance = Money.ZERO_PLN;

    BudgetBuilder(BudgetEvents budgetEvents, PlannedExpenses expenses, PlannedIncomes incomes) {
        this.budgetEvents = budgetEvents
        this.expenses = expenses
        this.incomes = incomes
    }

    Budget build() {
        return new Budget(REF_ID, LocalDate.MIN, LocalDate.MAX, balance, budgetEvents, BlaBudgetPolicy.Real, expenses, incomes)
    }

    BudgetBuilder balance(Money balance) {
        this.balance = balance
        this
    }

}
