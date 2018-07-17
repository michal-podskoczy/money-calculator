package com.podskoczy.money.budget.adapter;

import com.podskoczy.money.budget.BudgetService;
import com.podskoczy.money.expense.PlannedExpenseEvents;
import com.podskoczy.money.expense.PlannedExpenseOverdrawnEvent;
import com.podskoczy.money.expense.PlannedExpenseSpentAmountChangedEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlannedExpenseEventsListener implements PlannedExpenseEvents {

    private BudgetService budgetService;

    @Override
    public void emit(PlannedExpenseOverdrawnEvent plannedExpenseOverdrawnEvent) {
        //doesn't care
    }

    @Override
    public void emit(PlannedExpenseSpentAmountChangedEvent plannedExpenseSpentAmountChangedEvent) {
        budgetService.updateBalance(plannedExpenseSpentAmountChangedEvent.getRefId());
    }

}
