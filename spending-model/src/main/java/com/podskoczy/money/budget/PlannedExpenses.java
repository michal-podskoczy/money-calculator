package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.adapter.PlannedExpense;
import com.podskoczy.money.management.RefId;
import lombok.Value;

import java.util.Collection;

interface PlannedExpenses {

    //PlannedExpenseSum getPlannedExpensesSum(RefId refId);

    Collection<PlannedExpense> getPlannedExpenses(RefId refId);

    @Value
    class PlannedExpenseSum {
        private Money plannedAmount;
        private Money spentAmount;
    }

}
