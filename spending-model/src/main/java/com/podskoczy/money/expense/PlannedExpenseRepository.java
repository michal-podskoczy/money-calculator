package com.podskoczy.money.expense;

import com.podskoczy.money.management.RefId;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Michal on 2018-03-10.
 */
public interface PlannedExpenseRepository {

    Collection<PlannedExpense> get(Category category);

    Collection<PlannedExpense> get(RefId refId);

    Optional<PlannedExpense> get(RefId refId, Category category);

    void save(PlannedExpense plannedExpense);

}
