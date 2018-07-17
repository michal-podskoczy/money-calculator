package com.podskoczy.money.savings;

import com.podskoczy.money.expense.Expense;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 2018-02-22.
 */
@AllArgsConstructor
public class Expenses {

    List<Expense> get() {
        return new ArrayList<>();
    }

}
