package com.podskoczy.money.budget;

import com.podskoczy.money.Money;
import com.podskoczy.money.management.RefId;
import lombok.Value;

import java.time.LocalDate;

@Value
public class BudgetBalanceChangeEvent {

    private RefId refId;
    private LocalDate date;
    private Money previousBalance;
    private Money currentBalance;

}
