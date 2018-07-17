package com.podskoczy.money.income;

import com.podskoczy.money.Money;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class PlannedIncome {

    private PlannedIncomeEvents events;

    private Money plannedAmount;

    private Money realIncome;

    public void realIncome(Money realIncome) {
        if (!plannedAmount.equals(realIncome)) {
            events.emit(new PlannedIncomeChangedEvent());
        }

        this.realIncome = realIncome;
    }

    public Money getRealIncome() {
        return realIncome;
    }
}
