package com.podskoczy.money.budget.projection;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RepositoryRestResource(path = "budget_balances",
        collectionResourceRel = "budget_balances",
        itemResourceRel = "budget_balance")
public interface BudgetBalanceDao extends CrudRepository<BudgetBalanceEntity, String> {


    @RestResource(exported = false)
    void deleteByRefIdAndDate(String refId, LocalDate date);


}
