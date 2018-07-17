package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.RefId;
import lombok.Value;

import java.util.Set;

interface PlannedIncomes {

    PlannedIncome getPlannedIncomesSum(RefId refId);

    @Value
    class PlannedIncome {
        private Money plannedAmount;
        private Money realIncome;
    }
}
