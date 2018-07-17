package com.podskoczy.money.expense;

import com.podskoczy.money.expense.persistance.ExpenseDao;
import com.podskoczy.money.expense.persistance.ExpenseEntity;
import com.podskoczy.money.management.ExpenseId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ExpenseORMRepository implements ExpenseRepository {

    private ExpenseDao expenseDao;

    @Override
    public void save(Expense expense) {
        ExpenseEntity expenseEntity = map(expense);
        expenseDao.save(expenseEntity);
    }

    @Override
    public List<Expense> get(Category category, LocalDate dateFrom, LocalDate dateTo) {
        List<ExpenseEntity> expenseEntities = expenseDao.findByCategory(category);

        if(expenseEntities == null) {
            return Collections.emptyList();
        }

        return expenseEntities.stream().map(this::map).collect(Collectors.toList());
    }

    private ExpenseEntity map(Expense expense) {
        return new ExpenseEntity(expense.getId(), expense.getCategory(), expense.getDate(), expense.getMoney());
    }

    private Expense map(ExpenseEntity expenseEntity) {
        return Expense.of(ExpenseId.of(expenseEntity.getExpenseId()), expenseEntity.getDate(), expenseEntity.getMoney(), expenseEntity.getCategory());
    }

}
