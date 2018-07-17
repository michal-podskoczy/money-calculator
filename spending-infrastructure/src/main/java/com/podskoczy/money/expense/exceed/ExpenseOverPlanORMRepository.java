package com.podskoczy.money.expense.exceed;

import com.podskoczy.money.Currency;
import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Expense;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanRepository;
import com.podskoczy.money.expense.persistance.ExpenseOverPlanDao;
import com.podskoczy.money.expense.persistance.ExpenseOverPlanEntity;
import com.podskoczy.money.management.ExpenseId;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ExpenseOverPlanORMRepository implements ExpenseOverPlanRepository {

    private ExpenseOverPlanDao expenseOverPlanDao;

    @Override
    public void markExpenseOverPlan(RefId refId, List<Expense> expenses) {
        Collection<ExpenseOverPlanEntity> expenseOverPlanEntities = expenses.stream()
                .map(expense ->
                        new ExpenseOverPlanEntity(expense.getId(), refId, expense.getMoney())
                ).collect(Collectors.toList());

        expenseOverPlanDao.save(expenseOverPlanEntities);
    }

    @Override
    public List<ExpenseOverPlan> findExpensesOverPlan(RefId refId) {
        return expenseOverPlanDao.findExpensesOverPlanByRefId(refId).stream()
                .map(expenseOverPlanEntity -> new ExpenseOverPlan(
                                ExpenseId.of(expenseOverPlanEntity.getExpenseId()),
                                RefId.of(expenseOverPlanEntity.getRefId()),
                                Money.of(expenseOverPlanEntity.getAmount(), Currency.PLN) //FIXME fix currency
                        )
                )
                .collect(Collectors.toList());
    }
}
