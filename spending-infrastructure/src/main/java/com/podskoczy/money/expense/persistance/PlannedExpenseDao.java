package com.podskoczy.money.expense.persistance;

import com.podskoczy.money.expense.Category;
import com.podskoczy.money.expense.PlannedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michal on 2018-02-19.
 */
@Repository
@RepositoryRestResource(path = "planned-expenses",
        collectionResourceRel = "planned-expenses",
        itemResourceRel = "planned-expense")
public interface PlannedExpenseDao extends JpaRepository<PlannedExpenseEntity, String> {

    List<PlannedExpenseEntity> findByCategory(Category category);

    PlannedExpenseEntity findByCategoryAndRefId(Category category, String refId);

    List<PlannedExpenseEntity> findByRefId(String refId);

}
