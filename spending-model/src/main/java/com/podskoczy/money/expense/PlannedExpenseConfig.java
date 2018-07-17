package com.podskoczy.money.expense;

import com.podskoczy.money.expense.adapter.PlannedExpenseAdapter;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanRepository;
import com.podskoczy.money.expense.exceed.InMemoryExpenseOverPlanRepository;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public final class PlannedExpenseConfig {

    private final List<PlannedExpenseEvents> plannedExpenseEventsListeners = new ArrayList<>();

    public final PlannedExpenseEvents events = new PlannedExpenseEvents() {
        @Override
        public void emit(PlannedExpenseOverdrawnEvent plannedExpenseOverdrawnEvent) {
            plannedExpenseEventsListeners.forEach(plannedExpenseEvents -> plannedExpenseEvents.emit(plannedExpenseOverdrawnEvent));
        }

        @Override
        public void emit(PlannedExpenseSpentAmountChangedEvent plannedExpenseSpentAmountChangedEvent) {
            plannedExpenseEventsListeners.forEach(plannedExpenseEvents -> plannedExpenseEvents.emit(plannedExpenseSpentAmountChangedEvent));
        }
    };

    public PlannedExpenseService plannedExpenseService(ExpenseOverPlanMarker expenseOverPlanMarker, PlannedExpenseRepository plannedExpenseRepository, ExpenseRepository expenseRepository) {
        return new PlannedExpenseService(plannedExpenseRepository, events, expenseRepository, new PlannedExpenseResolver(plannedExpenseRepository), expenseOverPlanMarker);
    }

    public ExpenseOverPlanMarker expenseOverPlanMarker(ExpenseOverPlanRepository expenseOverPlanRepository) {
        return new ExpenseOverPlanMarker(expenseOverPlanRepository);
    }

    public PlannedExpenseAdapter plannedExpenseAdapter(PlannedExpenseRepository plannedExpenseRepository) {
        return new PlannedExpenseAdapterImpl(plannedExpenseRepository);
    }

    public PlannedExpenseConfig register(PlannedExpenseEvents listener) {
        plannedExpenseEventsListeners.add(listener);
        return this;
    }

    public InMemoryExpenseRepository inMemoryExpenseRepository() {
        return new InMemoryExpenseRepository();
    }

    public PlannedExpenseRepository inMemoryPlannedExpenseRepository() {
        return new InMemoryPlannedExpenseRepository();
    }

    public ExpenseOverPlanRepository inMemoryExpenseOverPlanRepository() {
        return new InMemoryExpenseOverPlanRepository();
    }

}
