package com.podskoczy.money.expense.adapter;

import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Category;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(of = {"category"})
public class PlannedExpense {

    private Category category;
    private Money plannedAmount;
    private Money spentAmount;

}
