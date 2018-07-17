package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
class Budget {

    RefId refId;

    LocalDate from;

    LocalDate to;

    Money balance;

    BudgetEvents budgetEvents;

    BlaBudgetPolicy budgetPolicy = BlaBudgetPolicy.Real;

    final PlannedExpenses expenses;

    final PlannedIncomes incomes;

    public RefId getRefId() {
        return refId;
    }

    public Money getBalance() {
        return balance;
    }

    void updateBalance() {
        State state = new State();

        Money expenseSum = calculateExpenseSum();
        Money incomeSum = budgetPolicy.getIncome(incomes.getPlannedIncomesSum(this.refId));

        this.balance = incomeSum.subtract(expenseSum);

        if (state.balanceChanged()) {
            budgetEvents.emit(state.getChange());
        }
    }

    private Money calculateExpenseSum() {
        return expenses.getPlannedExpenses(this.refId)
                .stream()
                .map(budgetPolicy::getExpense)
                .reduce(Money.ZERO_PLN, Money::add);
    }

    private class State {

        final Money balance;

        State() {
            this.balance = Budget.this.balance;
        }

        boolean balanceChanged() {
            return !Objects.equals(Budget.this.balance, this.balance);
        }

        BudgetBalanceChangeEvent getChange() {
            return new BudgetBalanceChangeEvent(Budget.this.refId, Budget.this.to, this.balance, Budget.this.balance);
        }
    }


}
