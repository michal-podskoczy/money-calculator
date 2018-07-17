package com.podskoczy.money.expense;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Michal on 2018-03-10.
 */
@AllArgsConstructor
public class PlannedExpenseService {

    private PlannedExpenseRepository plannedExpenseRepository;

    private PlannedExpenseEvents events;

    private ExpenseRepository expenseRepository;

    private PlannedExpenseResolver plannedExpenseResolver;

    private ExpenseOverPlanMarker expenseOverPlanMarker;

    public void register(Expense expense) {
        Collection<PlannedExpense> plannedExpenses = plannedExpenseResolver.resolve(expense);
        plannedExpenses.forEach(PlannedExpense::checkState);
    }

    public void checkState(Category category) {
        plannedExpenseRepository.get(category).forEach(plannedExpense -> {
            plannedExpense.checkState();
            plannedExpenseRepository.save(plannedExpense);
        });
    }

    public void checkState(RefId refId, Category category) {
        plannedExpenseRepository.get(refId, category).ifPresent(plannedExpense -> {
            plannedExpense.checkState();
            plannedExpenseRepository.save(plannedExpense);
        });
    }

    public void createPlannedExpense(RefId refId, LocalDate dateFrom, LocalDate dateTo, Category category, Money plannedAmount) {
        PlannedExpense plannedExpense = new PlannedExpense(refId, dateFrom, dateTo, category, plannedAmount, Money.ZERO_PLN, expenseRepository, events, expenseOverPlanMarker);

        plannedExpenseRepository.save(plannedExpense);
    }

}
