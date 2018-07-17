package com.podskoczy.money.budget;

import com.podskoczy.money.management.RefId;

import java.time.LocalDate;

interface BudgetRepository {

    Budget get(RefId refId);

    void save(Budget budget);

}
