package com.podskoczy.money.expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository {

    void save(Expense expense);

    List<Expense> get(Category category, LocalDate dateFrom, LocalDate dateTo);

}
