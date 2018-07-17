package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.adapter.PlannedExpense;

import static com.podskoczy.money.budget.PlannedExpenses.*;
import static com.podskoczy.money.budget.PlannedIncomes.*;

public enum BlaBudgetPolicy {

    Planned, Real;

    public Money getExpense(PlannedExpense plannedExpense) {
        switch (this) {
            case Planned:
                return plannedExpense.getPlannedAmount();
            case Real:
                return plannedExpense.getPlannedAmount().isBiggerThan(plannedExpense.getSpentAmount()) ?
                        plannedExpense.getPlannedAmount() : plannedExpense.getSpentAmount();
            default:
                throw new RuntimeException("Not supported policy.");
        }
    }

    public Money getIncome(PlannedIncome plannedIncome) {
        switch (this) {
            case Planned:
                return plannedIncome.getPlannedAmount();
            case Real:
                return plannedIncome.getPlannedAmount().isBiggerThan(plannedIncome.getRealIncome()) ?
                        plannedIncome.getPlannedAmount() : plannedIncome.getRealIncome();
            default:
                throw new RuntimeException("Not supported policy.");
        }
    }


}
