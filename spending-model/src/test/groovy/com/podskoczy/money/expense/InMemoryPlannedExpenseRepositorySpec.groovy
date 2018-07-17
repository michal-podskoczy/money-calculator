package com.podskoczy.money.expense

import com.podskoczy.money.Money
import com.podskoczy.money.management.RefId
import spock.lang.Specification

import java.time.LocalDate

import static com.podskoczy.money.Currency.PLN
import static com.podskoczy.money.Money.ZERO_PLN
import static com.podskoczy.money.Money.of
import static com.podskoczy.money.expense.Category.CREDIT
import static com.podskoczy.money.expense.Category.FOOD

class InMemoryPlannedExpenseRepositorySpec extends Specification {

    def repository = new InMemoryPlannedExpenseRepository()
    def refId = RefId.of()

    def "should update planned expense according to category"() {
        given: "food and credit expense in repository"
        repository.save(plannedExpense(CREDIT, ZERO_PLN))
        repository.save(plannedExpense(FOOD, ZERO_PLN))

        def updatedFoodExpense = plannedExpense(FOOD, of(50, PLN))

        when: "food is updated with new value"
        repository.save(updatedFoodExpense)

        then: "repository contains updated value"
        updatedFoodExpense == repository.get(refId, FOOD).get()
    }

    PlannedExpense plannedExpense(Category category, Money spentAmount) {
        new PlannedExpense(refId, LocalDate.MIN, LocalDate.MAX, category, of(100, PLN), spentAmount, null, null, null)
    }


}
