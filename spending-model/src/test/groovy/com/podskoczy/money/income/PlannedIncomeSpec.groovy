package com.podskoczy.money.income

import com.podskoczy.money.Money
import spock.lang.Specification

import static com.podskoczy.money.Currency.PLN


class PlannedIncomeSpec extends Specification {

    PlannedIncomeEvents events

    def setup() {
        events = Mock(PlannedIncomeEvents)
    }

    def "should emit event when real income is different than planned"() {
        given:
        PlannedIncome plannedIncome = new PlannedIncome(events, Money.of(150, PLN), Money.ZERO_PLN)

        when:
        plannedIncome.realIncome(Money.of(3444, PLN))

        then:

        1 * events.emit(new PlannedIncomeChangedEvent())
    }


}
