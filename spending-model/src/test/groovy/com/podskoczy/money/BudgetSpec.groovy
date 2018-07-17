package com.podskoczy.money

import com.podskoczy.money.budget.BudgetConfig
import com.podskoczy.money.budget.BudgetService
import com.podskoczy.money.expense.Expense
import com.podskoczy.money.expense.ExpenseRepository
import com.podskoczy.money.expense.PlannedExpenseConfig
import com.podskoczy.money.expense.PlannedExpenseService
import com.podskoczy.money.expense.exceed.ExpenseOverPlanRepository
import com.podskoczy.money.management.ExpenseId
import com.podskoczy.money.management.RefId
import spock.lang.Specification

import java.time.LocalDate

import static com.podskoczy.money.Currency.PLN
import static com.podskoczy.money.Money.of
import static com.podskoczy.money.expense.Category.CREDIT
import static com.podskoczy.money.expense.Category.FOOD

class BudgetSpec extends Specification {

    static final Money PLANNED_CREDIT_AMOUNT = of(1722.65, PLN)
    static final Money PLANNED_FOOD_AMOUNT = of(1500, PLN)
    static final Money FOOD_EXPENSE_AMOUNT = of(1600, PLN)

    PlannedExpenseConfig plannedExpenseConfig
    PlannedExpenseService plannedExpenseService
    BudgetConfig budgetConfig
    BudgetService budgetService
    ExpenseRepository expenseRepository
    ExpenseOverPlanRepository expenseOverPlanRepository

    def dateFrom = LocalDate.of(2018, 05, 1)
    def dateTo = LocalDate.of(2018, 05, 31)

    def setup() {
        plannedExpenseConfig = new PlannedExpenseConfig()
        def plannedExpenseRepository = plannedExpenseConfig.inMemoryPlannedExpenseRepository()

        budgetConfig = new BudgetConfig()

        budgetService = budgetConfig.budgetService(budgetConfig.inMemoryBudgetRepository(), plannedExpenseConfig.plannedExpenseAdapter(plannedExpenseRepository))

        plannedExpenseConfig.register(budgetConfig.plannedExpenseEventsListener(budgetService))

        expenseOverPlanRepository = plannedExpenseConfig.inMemoryExpenseOverPlanRepository()

        expenseRepository = plannedExpenseConfig.inMemoryExpenseRepository()
        def expenseOverPlanMarker = plannedExpenseConfig.expenseOverPlanMarker(expenseOverPlanRepository)
        plannedExpenseService = plannedExpenseConfig.plannedExpenseService(expenseOverPlanMarker, plannedExpenseRepository, expenseRepository)
    }

    def "should calculate budget balance"() {
        given: "budget with planned expenseRepository"

        RefId refId = budgetService.createBudget(dateFrom, dateTo)
        plannedExpenseService.createPlannedExpense(refId, dateFrom, dateTo, CREDIT, PLANNED_CREDIT_AMOUNT)
        plannedExpenseService.createPlannedExpense(refId, dateFrom, dateTo, FOOD, PLANNED_FOOD_AMOUNT)

        and: "expense over plan"
        Expense expense = Expense.of(dateFrom.plusDays(1), FOOD_EXPENSE_AMOUNT, FOOD)

        expenseRepository.save(expense) //rozdzielenie pozwala na korzystanie z @RestRepository
        plannedExpenseService.register(expense)

        when: "calculating balance"
        Money balance = budgetService.getBalance(refId)

        then: "returns correct balance"
        balance == PLANNED_CREDIT_AMOUNT.add(FOOD_EXPENSE_AMOUNT).negate()
    }

    def "should mark expense as over limit"() {
        given: "budget with planned expenseRepository"
        RefId refId = budgetService.createBudget(dateFrom, dateTo)
        plannedExpenseService.createPlannedExpense(refId, dateFrom, dateTo, CREDIT, PLANNED_CREDIT_AMOUNT)
        plannedExpenseService.createPlannedExpense(refId, dateFrom, dateTo, FOOD, PLANNED_FOOD_AMOUNT)

        and: "expense over plan"
        Expense expense = Expense.of(dateFrom.plusDays(1), FOOD_EXPENSE_AMOUNT, FOOD)
        ExpenseId expenseId = expense.id

        when: "register expense over plan"
        expenseRepository.save(expense)
        plannedExpenseService.register(expense)

        then: "expense is marked"
        List<ExpenseOverPlanRepository.ExpenseOverPlan> expensesOverPlan = expenseOverPlanRepository.findExpensesOverPlan(refId)
        expensesOverPlan.size() == 1
        expensesOverPlan.get(0).id == expenseId
        expensesOverPlan.get(0).refId == refId
    }


}
