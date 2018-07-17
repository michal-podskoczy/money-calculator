package com.podskoczy.money.budget;

import com.podskoczy.money.expense.PlannedExpenseEvents;
import com.podskoczy.money.expense.adapter.PlannedExpenseAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class BudgetConfiguration {

    private BudgetConfig budgetConfig = new BudgetConfig();

    @Autowired
    private BudgetRepository budgetRepository;

    @Bean
    public PlannedExpenseEvents plannedExpenseEventsListener(BudgetService budgetService) {
        return budgetConfig.plannedExpenseEventsListener(budgetService);
    }

    @Bean
    public BudgetService budgetService(PlannedExpenseAdapter plannedExpenseAdapter) {
        return budgetConfig.budgetService(budgetRepository, plannedExpenseAdapter);
    }


}
