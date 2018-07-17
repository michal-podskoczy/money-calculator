package com.podskoczy.money.expense;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.adapter.PlannedExpense;
import com.podskoczy.money.expense.adapter.PlannedExpenseAdapter;
import com.podskoczy.money.expense.adapter.PlannedExpenseSummary;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class PlannedExpenseAdapterImpl implements PlannedExpenseAdapter {

    private PlannedExpenseRepository plannedExpenseRepository;

    @Override
    public PlannedExpenseSummary getPlannedExpenseSummary(RefId refId) {
        return plannedExpenseRepository.get(refId).stream()
                .map(plannedExpense -> new PlannedExpenseSummary(plannedExpense.getPlannedAmount(), plannedExpense.getSpentAmount()))
                .reduce(new PlannedExpenseSummary(Money.ZERO_PLN, Money.ZERO_PLN),
                        (sum1, sum2) -> new PlannedExpenseSummary(sum1.getPlannedAmount().add(sum2.getPlannedAmount()), sum1.getSpentAmount().add(sum2.getSpentAmount())),
                        (sum1, sum2) -> new PlannedExpenseSummary(sum1.getPlannedAmount().add(sum2.getPlannedAmount()), sum1.getSpentAmount().add(sum2.getSpentAmount())));
    }

    @Override
    public Set<PlannedExpense> getPlannedExpenses(RefId refId) {
        return plannedExpenseRepository.get(refId).stream()
                .map(plannedExpense -> new PlannedExpense(plannedExpense.getCategory(), plannedExpense.getPlannedAmount(), plannedExpense.getSpentAmount()))
                .collect(Collectors.toSet());
    }

}
