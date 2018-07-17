package com.podskoczy.money.expense.exceed;

import com.podskoczy.money.expense.Expense;
import com.podskoczy.money.management.RefId;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InMemoryExpenseOverPlanRepository implements ExpenseOverPlanRepository {

    private ConcurrentMap<RefId, List<ExpenseOverPlan>> map = new ConcurrentHashMap<>();

    @Override
    public void markExpenseOverPlan(RefId refId, List<Expense> expenses) {

        List<ExpenseOverPlan> expensesOverPlan = expenses.stream()
                .map(expense -> new ExpenseOverPlan(expense.getId(), refId, expense.getMoney()))
                .collect(Collectors.toList());

        map.merge(refId, expensesOverPlan, this::concat);
    }

    @Override
    public List<ExpenseOverPlan> findExpensesOverPlan(RefId refId) {
        return map.get(refId);
    }

    private <T> List<T> concat(List<T> list, List<T> secondList) {
        List<T> concatedList = new ArrayList<>();
        concatedList.addAll(list);
        concatedList.addAll(secondList);
        return concatedList;
    }

}
