package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class BudgetService {

    private BudgetRepository budgetRepository;

    private BudgetEvents budgetEvents;

    private PlannedExpenses plannedExpenses;

    private PlannedIncomes plannedIncomes;

    public void updateBalance(RefId refId) {
        Budget budget = budgetRepository.get(refId);
        budget.updateBalance();
        budgetRepository.save(budget);
    }

    public Money getBalance(RefId refId) {
        Budget budget = budgetRepository.get(refId);
        return budget.getBalance();
    }

    public RefId createBudget(LocalDate dateFrom, LocalDate dateTo) {
        Budget budget = new Budget(RefId.of(), dateFrom, dateTo, Money.ZERO_PLN, budgetEvents, BlaBudgetPolicy.Real, plannedExpenses, plannedIncomes);
        budgetRepository.save(budget);
        return budget.getRefId();
    }


}
