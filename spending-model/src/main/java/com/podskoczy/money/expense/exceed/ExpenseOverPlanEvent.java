package com.podskoczy.money.expense.exceed;

import com.podskoczy.money.management.ExpenseId;
import com.podskoczy.money.management.RefId;
import lombok.Value;

@Value
public class ExpenseOverPlanEvent {

    private RefId refId;

    private ExpenseId id;

}
