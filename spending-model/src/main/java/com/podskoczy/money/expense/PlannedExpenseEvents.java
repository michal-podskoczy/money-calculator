package com.podskoczy.money.expense;

public interface PlannedExpenseEvents {

    void emit(PlannedExpenseOverdrawnEvent plannedExpenseOverdrawnEvent);

    void emit(PlannedExpenseSpentAmountChangedEvent plannedExpenseSpentAmountChangedEvent);

}
