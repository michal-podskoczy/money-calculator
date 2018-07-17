package com.podskoczy.money.expense.persistance;

import com.podskoczy.money.expense.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michal on 2018-02-11.
 */
@Repository
@RepositoryRestResource(path = "incomes",
        collectionResourceRel = "incomes",
        itemResourceRel = "income")
public interface IncomeDao extends JpaRepository<ExpenseEntity, String>{

    List<ExpenseEntity> findByCategory(Category category);

}
