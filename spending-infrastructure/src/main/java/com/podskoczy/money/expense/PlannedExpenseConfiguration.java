package com.podskoczy.money.expense;

import com.podskoczy.money.expense.adapter.PlannedExpenseAdapter;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanORMRepository;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanRepository;
import com.podskoczy.money.expense.persistance.ExpenseDao;
import com.podskoczy.money.expense.persistance.ExpenseOverPlanDao;
import com.podskoczy.money.expense.persistance.PlannedExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Michal on 2018-03-10.
 */
@Configuration
public class PlannedExpenseConfiguration {

    private PlannedExpenseConfig plannedExpenseConfig = new PlannedExpenseConfig();

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private PlannedExpenseDao plannedExpenseDao;

    @Autowired
    private ExpenseOverPlanDao expenseOverPlanDao;

    @Bean
    public ExpenseOverPlanRepository expenseOverPlanRepository(ExpenseOverPlanDao expenseOverPlanDao) {
        return new ExpenseOverPlanORMRepository(expenseOverPlanDao);
    }

    @Bean
    public PlannedExpenseRepository plannedExpenseRepository(PlannedExpenseDao plannedExpenseDao, ExpenseDao expenseDao, PlannedExpenseEvents plannedExpenseEvents, ExpenseOverPlanMarker expenseOverPlanMarker, ExpenseRepository expenseRepository) {
        return new PlannedExpenseORMRepository(plannedExpenseDao, expenseDao, plannedExpenseEvents, expenseOverPlanMarker, expenseRepository);
    }

    @Bean
    public PlannedExpenseEvents plannedExpenseEvents() {
        return plannedExpenseConfig.events;
    }

    @Bean
    public ExpenseRepository expenseRepository(ExpenseDao expenseDao) {
        return new ExpenseORMRepository(expenseDao);
    }

    @Bean
    public PlannedExpenseService plannedExpenseService(ExpenseOverPlanMarker expenseOverPlanMarker, PlannedExpenseRepository plannedExpenseRepository, ExpenseRepository expenseRepository) {
        return plannedExpenseConfig.plannedExpenseService(expenseOverPlanMarker, plannedExpenseRepository, expenseRepository);
    }

    @Bean
    public PlannedExpenseAdapter plannedExpenseAdapter(PlannedExpenseRepository plannedExpenseRepository) {
        return plannedExpenseConfig.plannedExpenseAdapter(plannedExpenseRepository);
    }

    @Bean
    public ExpenseOverPlanMarker expenseOverPlanMarker(ExpenseOverPlanRepository expenseOverPlanRepository) {
        return plannedExpenseConfig.expenseOverPlanMarker(expenseOverPlanRepository);
    }


}
