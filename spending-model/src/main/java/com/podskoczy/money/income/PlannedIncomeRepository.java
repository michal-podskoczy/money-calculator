package com.podskoczy.money.income;

import com.podskoczy.money.expense.Category;
import com.podskoczy.money.management.RefId;

import java.util.Collection;

public interface PlannedIncomeRepository {

    Collection<PlannedIncome> get(RefId refId);

    PlannedIncome get(String refId, Category category);

    void save(PlannedIncome plannedExpense);

}
