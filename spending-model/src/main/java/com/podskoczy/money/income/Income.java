package com.podskoczy.money.income;


import com.podskoczy.money.Money;
import com.podskoczy.money.expense.Category;
import lombok.Value;

import java.time.LocalDate;

/**
 * Created by Michal on 2018-01-13.
 */
@Value
public class Income {

    private LocalDate date;

    private Money money;

    private Category category;

    public static Income of(LocalDate date, Money money, Category category) {
        return new Income(date, money, category);
    }

}
