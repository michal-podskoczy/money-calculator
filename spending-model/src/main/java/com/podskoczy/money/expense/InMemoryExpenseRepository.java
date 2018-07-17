package com.podskoczy.money.expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class InMemoryExpenseRepository implements ExpenseRepository {

    private ConcurrentMap<Category, List<Expense>> data = new ConcurrentHashMap<>();

    @Override
    public void save(Expense expense) {
        data.merge(expense.getCategory(), Collections.singletonList(expense), (oldList, newList) -> {
            List<Expense> list = new ArrayList<>(oldList);
            list.addAll(oldList);
            return list;
        });
    }

    @Override
    public List<Expense> get(Category category, LocalDate dateFrom, LocalDate dateTo) {
        return data.get(category);
    }

}
