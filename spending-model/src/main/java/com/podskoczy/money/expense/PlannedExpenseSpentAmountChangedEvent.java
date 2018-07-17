package com.podskoczy.money.expense;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.RefId;
import lombok.Value;

@Value
public class PlannedExpenseSpentAmountChangedEvent {

    private RefId refId;

    private Category category;

    private SpentAmountChange spentAmountChange;

    @Value
    public static class SpentAmountChange {

        private Money previous;

        private Money current;

    }

}
