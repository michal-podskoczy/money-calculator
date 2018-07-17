package com.podskoczy.money.budget;

import com.podskoczy.money.budget.projection.BudgetBalanceProjection;
import com.podskoczy.money.expense.PlannedExpenseEvents;
import com.podskoczy.money.expense.PlannedExpenseOverdrawnEvent;
import com.podskoczy.money.expense.PlannedExpenseSpentAmountChangedEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BudgetEventPropagation implements BudgetEvents {

    private final BudgetBalanceProjection budgetBalanceProjection;

    @Override
    public void emit(BudgetBalanceChangeEvent budgetBalanceChangeEvent) {
        budgetBalanceProjection.applyBudgetBalanceChange(budgetBalanceChangeEvent);
    }

}
