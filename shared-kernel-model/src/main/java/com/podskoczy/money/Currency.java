package com.podskoczy.money;

import lombok.Value;

/**
 * Created by Michal on 2018-01-13.
 */
@Value
public class Currency {

    private Currency(String code) {
        this.code = code;
    }

    private static final Currency PLN_VALUE = new Currency("PLN");

    private String code;

    public static Currency PLN = PLN_VALUE;

}
