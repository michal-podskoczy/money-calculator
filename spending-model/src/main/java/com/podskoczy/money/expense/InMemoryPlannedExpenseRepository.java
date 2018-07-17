package com.podskoczy.money.expense;

import com.podskoczy.money.management.RefId;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

class InMemoryPlannedExpenseRepository implements PlannedExpenseRepository {

    private ConcurrentMap<RefId, List<PlannedExpense>> plannedExpenses = new ConcurrentHashMap<>();

    @Override
    public Collection<PlannedExpense> get(Category category) {
        return plannedExpenses.values().stream()
                .flatMap(List::stream)
                .filter(plannedExpense -> plannedExpense.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<PlannedExpense> get(RefId refId) {
        Collection<PlannedExpense> ret = plannedExpenses.get(refId);
        return ret == null ? Collections.emptyList() : ret;
    }

    @Override
    public Optional<PlannedExpense> get(RefId refId, Category category) {
        return plannedExpenses.get(refId).stream().filter(plannedExpense -> plannedExpense.getCategory() == category).findFirst();
    }

    @Override
    public void save(PlannedExpense plannedExpense) {
        plannedExpenses.merge(plannedExpense.getRefId(), Collections.singletonList(plannedExpense), (oldList, newList) -> {
            List<PlannedExpense> listWithoutSavedElement = oldList.stream()
                    .filter(p -> p.getCategory() != plannedExpense.getCategory())
                    .collect(Collectors.toList());
            listWithoutSavedElement.addAll(newList);
            return listWithoutSavedElement;
        });
    }


}
