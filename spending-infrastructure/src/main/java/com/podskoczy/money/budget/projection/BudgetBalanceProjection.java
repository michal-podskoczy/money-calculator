package com.podskoczy.money.budget.projection;

import com.podskoczy.money.budget.BudgetBalanceChangeEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BudgetBalanceProjection {

    private BudgetBalanceDao budgetBalanceDao;

    public void applyBudgetBalanceChange(BudgetBalanceChangeEvent event) {
        budgetBalanceDao.deleteByRefIdAndDate(event.getRefId().getRefId(), event.getDate());

        BudgetBalanceEntity budgetBalanceEntity = new BudgetBalanceEntity(event.getRefId().getRefId(), event.getDate(), event.getCurrentBalance());
        budgetBalanceDao.save(budgetBalanceEntity);
    }

}
