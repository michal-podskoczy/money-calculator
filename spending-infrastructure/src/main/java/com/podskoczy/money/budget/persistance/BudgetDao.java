package com.podskoczy.money.budget.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "budgets",
        collectionResourceRel = "budgets",
        itemResourceRel = "budget")
public interface BudgetDao extends JpaRepository<BudgetEntity, String> {

    BudgetEntity findByRefId(String refId);

}
