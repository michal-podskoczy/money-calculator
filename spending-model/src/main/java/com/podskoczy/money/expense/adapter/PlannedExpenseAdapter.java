package com.podskoczy.money.expense.adapter;

import com.podskoczy.money.management.RefId;

import java.util.Set;

public interface PlannedExpenseAdapter {

    PlannedExpenseSummary getPlannedExpenseSummary(RefId refId);

    Set<PlannedExpense> getPlannedExpenses(RefId refId);

}
