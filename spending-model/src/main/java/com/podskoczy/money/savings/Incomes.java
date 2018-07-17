package com.podskoczy.money.savings;

import com.podskoczy.money.income.Income;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 2018-02-22.
 */
@AllArgsConstructor
public class Incomes {

    List<Income> get() {
        return new ArrayList<>();
    }

}
