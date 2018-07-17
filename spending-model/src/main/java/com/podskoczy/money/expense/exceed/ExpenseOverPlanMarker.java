package com.podskoczy.money.expense.exceed;

import com.podskoczy.money.expense.Expense;
import com.podskoczy.money.management.RefId;
import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExpenseOverPlanMarker {

    public ExpenseOverPlanMarker(ExpenseOverPlanRepository expenseOverPlanRepository) {
        this.expenseOverPlanRepository = expenseOverPlanRepository;
    }

    private ExpenseOverPlanRepository expenseOverPlanRepository;

    private ExpenseOverPlanMarkerPolicy expenseOverPlanMarkerPolicy = ExpenseOverPlanMarkerPolicy.ALL;

    public void markExpense(RefId refId, Expense expense) {
        this.markExpenses(refId, Collections.singletonList(expense));
    }

    public void markExpenses(RefId refId, List<Expense> expenses) {
        expenseOverPlanRepository.markExpenseOverPlan(refId, expenses);
    }

    enum ExpenseOverPlanMarkerPolicy {
        LAST,
        ALL
    }

}
