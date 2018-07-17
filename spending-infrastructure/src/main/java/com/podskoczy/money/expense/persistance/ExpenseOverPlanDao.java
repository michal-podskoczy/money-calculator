package com.podskoczy.money.expense.persistance;

import com.podskoczy.money.management.RefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@RepositoryRestResource(path = "expenses-over-plan",
        collectionResourceRel = "expenses-over-plan",
        itemResourceRel = "expenseOverPlan")
public interface ExpenseOverPlanDao extends JpaRepository<ExpenseOverPlanDao, String> {

    void save(Collection<ExpenseOverPlanEntity> expensesOverPlan);

    List<ExpenseOverPlanEntity> findExpensesOverPlanByRefId(RefId refId);


}
