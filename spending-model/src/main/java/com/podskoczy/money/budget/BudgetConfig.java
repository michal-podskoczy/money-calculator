package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.budget.adapter.PlannedExpenseEventsListener;
import com.podskoczy.money.expense.adapter.PlannedExpenseAdapter;
import com.podskoczy.money.management.RefId;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class BudgetConfig {

    public BudgetRepository inMemoryBudgetRepository() {
        return new InMemoryBudgetRepository();
    }

    public BudgetService budgetService(BudgetRepository budgetRepository, PlannedExpenseAdapter plannedExpenseAdapter) {
        return new BudgetService(
            budgetRepository,
                (BudgetBalanceChangeEvent event) -> {},
                plannedExpenseAdapter::getPlannedExpenses,
                (RefId refId) -> new PlannedIncomes.PlannedIncome(Money.ZERO_PLN, Money.ZERO_PLN));
    }

    public PlannedExpenseEventsListener plannedExpenseEventsListener(BudgetService budgetService) {
        return new PlannedExpenseEventsListener(budgetService);
    }

}
