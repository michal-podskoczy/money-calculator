package com.podskoczy.money.expense;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExpenseService {

    private PlannedExpenseService plannedExpenseService;

    private ExpenseRepository expenseRepository;

    public void register(Expense expense) {
        plannedExpenseService.checkState(expense.getCategory());
    }

}
