package com.podskoczy.money.budget;

import com.podskoczy.money.Currency;
import com.podskoczy.money.Money;
import com.podskoczy.money.budget.persistance.BudgetDao;
import com.podskoczy.money.budget.persistance.BudgetEntity;
import com.podskoczy.money.expense.adapter.PlannedExpenseAdapter;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BudgetORMRepository implements BudgetRepository {

    private BudgetDao budgetDao;

    private BudgetEvents budgetEvents;

    private PlannedExpenseAdapter plannedExpenseAdapter;

    @Override
    public Budget get(RefId refId) {
        BudgetEntity budgetEntity = budgetDao.findByRefId(refId.getRefId());

        return new Budget(refId, budgetEntity.getDateFrom(), budgetEntity.getDateTo(),
                Money.of(budgetEntity.getBalanceAmount(), Currency.PLN), budgetEvents, BlaBudgetPolicy.Real,

                plannedExpenseAdapter::getPlannedExpenses, null);
    }

    @Override
    public void save(Budget budget) {
        BudgetEntity budgetEntity = budgetDao.findByRefId(budget.getRefId().getRefId());
        if (budgetEntity == null) {
            budgetEntity = new BudgetEntity(budget.refId.getRefId(), budget.from, budget.to, budget.balance.getAmount(), budget.balance.getCurrency().getCode());
        }

        budgetEntity.setBalanceAmount(budget.balance.getAmount());
        budgetEntity.setBalanceCurrency(budget.balance.getCurrency().getCode());

        budgetDao.save(budgetEntity);
    }
}
