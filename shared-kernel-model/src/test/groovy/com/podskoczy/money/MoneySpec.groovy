package com.podskoczy.money

import spock.lang.Specification

import static com.podskoczy.money.Currency.PLN

/**
 * Created by Michal on 2018-01-14.
 */
class MoneySpec extends Specification {

    def "should add money"() {
        expect:
        Money.of(amount, PLN).add(Money.of(secondAmount, PLN)) == Money.of(sum, PLN)

        where:
        amount | secondAmount || sum
        100    | 100          || 200
        200    | 300          || 500
        150    | 150          || 300
    }

    def "should subtract money"() {
        expect:
        Money.of(amount, PLN).subtract(Money.of(secondAmount, PLN)) == Money.of(difference, PLN)

        where:
        amount | secondAmount || difference
        100    | 100          || 0
        200    | 300          || -100
        200    | 150          || 50
    }

    def "should compare to find bigger"() {
        expect:
        Money.of(amount, PLN).isBiggerThan(Money.of(secondAmount, PLN)) == result

        where:
        amount | secondAmount || result
        100    | 100          || false
        200    | 150          || true
        200    | 250          || false
    }

    def "should negate value"() {
        expect:
        Money.of(amount, PLN).negate() == Money.of(negativeAmount, PLN)

        where:
        amount || negativeAmount
        100    || -100
        200    || -200
        0      || 0
    }

}
