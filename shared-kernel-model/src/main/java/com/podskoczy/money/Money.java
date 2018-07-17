package com.podskoczy.money;

import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by Michal on 2018-01-13.
 */
@Value
@ToString
public class Money implements Comparable<Money> {

    private BigDecimal amount;

    private Currency currency;

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    public static Money of(double amount, Currency currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    public Money add(Money money) {
        if(!this.currency.equals(money.currency)) {
            throw new RuntimeException("Not implemented yet.");
        }

        return Money.of(this.amount.add(money.amount), this.currency);
    }

    public Money subtract(Money money) {
        if(!this.currency.equals(money.currency)) {
            throw new RuntimeException("Not implemented yet.");
        }

        return Money.of(this.amount.subtract(money.amount), this.currency);
    }

    public Money negate() {
        return Money.of(this.amount.negate(), this.currency);
    }

    public static final Money ZERO_PLN = Money.of(0.0, Currency.PLN);

    public boolean isBiggerThan(Money other) {
        return this.compareTo(other) == 1;
    }

    @Override
    public int compareTo(Money other) {
        if(!this.currency.equals(other.currency)) {
            throw new RuntimeException("Not implemented yet.");
        }
        return this.amount.compareTo(other.amount);
    }
}
