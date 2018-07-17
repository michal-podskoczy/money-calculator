package com.podskoczy.money.expense;

import com.podskoczy.money.Money;

import com.podskoczy.money.expense.PlannedExpenseSpentAmountChangedEvent.SpentAmountChange;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by Michal on 2018-01-31.
 */
@AllArgsConstructor
@EqualsAndHashCode(of = {"refId", "category", "plannedAmount", "spentAmount"})
class PlannedExpense {

    private RefId refId;

    LocalDate from;

    LocalDate to;

    private Category category;

    private Money plannedAmount;

    private Money spentAmount;

    private ExpenseRepository expenseRepository;

    private PlannedExpenseEvents plannedExpenseEvents;

    private ExpenseOverPlanMarker expenseOverPlanMarker;

    void checkState() {
        State state = new State();

        List<Expense> expenseCollection = expenseRepository.get(this.category, this.from, this.to);
        this.spentAmount = calculateTotalExpense();

        if (state.isOverdrawn()) {
            expenseOverPlanMarker.markExpenses(this.refId, expenseCollection);
            plannedExpenseEvents.emit(PlannedExpenseOverdrawnEvent.of(this.refId, this.from, this.to, this.category));
        }

        if (state.spentAmountChanged()) {
            plannedExpenseEvents.emit(new PlannedExpenseSpentAmountChangedEvent(this.refId, this.category, state.getSpentAmountChange()));
        }
    }

    private Money calculateTotalExpense() {
        Collection<Expense> expenses = this.expenseRepository.get(this.category, this.from, this.to);

        if(expenses == null) {
            return Money.ZERO_PLN;
        }

        return expenses.stream()
                .map(Expense::getMoney)
                .reduce(Money.ZERO_PLN, Money::add);
    }

    public RefId getRefId() {
        return refId;
    }

    public Category getCategory() {
        return category;
    }

    public Money getSpentAmount() {
        return spentAmount;
    }

    public Money getPlannedAmount() {
        return plannedAmount;
    }

    private class State {

        final Money plannedAmount;

        final Money spentAmount;

        State() {
            this.plannedAmount = PlannedExpense.this.plannedAmount;
            this.spentAmount = PlannedExpense.this.spentAmount;
        }

        boolean plannedAmountChanged() {
            return !Objects.equals(PlannedExpense.this.plannedAmount, this.plannedAmount);
        }

        boolean spentAmountChanged() {
            return !Objects.equals(PlannedExpense.this.spentAmount, this.spentAmount);
        }

        boolean isOverdrawn() {
            return PlannedExpense.this.spentAmount.isBiggerThan(plannedAmount);
        }

        SpentAmountChange getSpentAmountChange() {
            return new SpentAmountChange(this.spentAmount, PlannedExpense.this.spentAmount);
        }


    }

}
