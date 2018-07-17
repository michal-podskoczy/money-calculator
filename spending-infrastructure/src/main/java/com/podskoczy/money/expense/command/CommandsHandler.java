package com.podskoczy.money.expense.command;

import com.podskoczy.money.Currency;
import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Expense;
import com.podskoczy.money.expense.PlannedExpenseService;
import com.podskoczy.money.expense.persistance.ExpenseEntity;
import com.podskoczy.money.management.ExpenseId;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Michal on 2018-02-24.
 */
@Component
@AllArgsConstructor
@RepositoryEventHandler
public class CommandsHandler {

    private PlannedExpenseService plannedExpenseService;

    @HandleBeforeCreate
    @HandleAfterSave
    public void create(ExpenseEntity expenseEntity) {
        plannedExpenseService.register(mapToExpense(expenseEntity));

        System.out.println("Expense saved: " + expenseEntity.getAmount());
    }

    private Expense mapToExpense(ExpenseEntity expenseEntity) {
        return Expense.of(ExpenseId.of(expenseEntity.getExpenseId()), expenseEntity.getDate(), Money.of(expenseEntity.getAmount(), Currency.PLN), expenseEntity.getCategory());
    }


}
