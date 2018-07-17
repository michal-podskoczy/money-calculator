package com.podskoczy.money.expense;

import com.podskoczy.money.budget.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class ExpenseEventPropagation implements PlannedExpenseEvents {

    private final BudgetService budgetService;

    @Override
    public void emit(PlannedExpenseOverdrawnEvent event) {
        // TODO - dodac zapisywanie transakcji ktore przekroczyly zaplanowany budget
    }

    @Override
    public void emit(PlannedExpenseSpentAmountChangedEvent event) {
        budgetService.updateBalance(event.getRefId());
    }

}
