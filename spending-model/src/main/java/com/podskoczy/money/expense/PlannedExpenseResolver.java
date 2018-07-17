package com.podskoczy.money.expense;

import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class PlannedExpenseResolver {

    private PlannedExpenseRepository plannedExpenseRepository;

    //resolve by category
    Collection<PlannedExpense> resolve(Expense expense) {
        return plannedExpenseRepository.get(expense.getCategory());
    }

}
