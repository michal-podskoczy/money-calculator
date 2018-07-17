package com.podskoczy.money.budget;

public interface BudgetEvents {

    void emit(BudgetBalanceChangeEvent budgetBalanceChangeEvent);

}
