package com.podskoczy.money.income;

import com.podskoczy.money.expense.Category;
import com.podskoczy.money.management.RefId;

import java.util.Collection;

public class PlannedIncomeORMRepository implements PlannedIncomeRepository {

    @Override
    public Collection<PlannedIncome> get(RefId refId) {
        return null;
    }

    @Override
    public PlannedIncome get(String refId, Category category) {
        return null;
    }

    @Override
    public void save(PlannedIncome plannedExpense) {

    }
}
