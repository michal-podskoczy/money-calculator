package com.podskoczy.money.expense.exceed;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Expense;
import com.podskoczy.money.management.ExpenseId;
import com.podskoczy.money.management.RefId;
import lombok.Value;

import java.util.List;

public interface ExpenseOverPlanRepository {

    void markExpenseOverPlan(RefId refId, List<Expense> expenses);

    List<ExpenseOverPlan> findExpensesOverPlan(RefId refId);

    @Value
    class ExpenseOverPlan {
        private ExpenseId id;
        private RefId refId;
        private Money amount;
    }

}
