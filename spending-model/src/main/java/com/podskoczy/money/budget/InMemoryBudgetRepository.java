package com.podskoczy.money.budget;

import com.podskoczy.money.management.RefId;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class InMemoryBudgetRepository implements BudgetRepository {

    private ConcurrentMap<RefId, Budget> map = new ConcurrentHashMap<>();

    @Override
    public Budget get(RefId refId) {
        return map.get(refId);
    }

    @Override
    public void save(Budget budget) {
        map.put(budget.getRefId(), budget);
    }

}
