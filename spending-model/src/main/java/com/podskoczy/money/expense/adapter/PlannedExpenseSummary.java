package com.podskoczy.money.expense.adapter;

import com.podskoczy.money.Money;
import lombok.Value;

@Value
public class PlannedExpenseSummary {

    private Money plannedAmount;
    private Money spentAmount;

}
