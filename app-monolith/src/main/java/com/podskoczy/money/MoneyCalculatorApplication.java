package com.podskoczy.money;

import com.podskoczy.money.budget.BudgetService;
import com.podskoczy.money.expense.*;

import com.podskoczy.money.expense.exceed.ExpenseOverPlanRepository;
import com.podskoczy.money.expense.persistance.ExpenseDao;
import com.podskoczy.money.expense.persistance.ExpenseEntity;
import com.podskoczy.money.expense.persistance.PlannedExpenseDao;

import com.podskoczy.money.management.ExpenseId;
import com.podskoczy.money.management.RefId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

import static com.podskoczy.money.Currency.PLN;
import static com.podskoczy.money.Money.of;
import static com.podskoczy.money.expense.Category.CREDIT;
import static com.podskoczy.money.expense.Category.FOOD;

/**
 * Created by Michal on 2018-02-05.
 */
@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses = {MoneyCalculatorApplication.class})
public class MoneyCalculatorApplication implements ApplicationRunner {

    public static final RefId REF_ID = new RefId("refId");

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private PlannedExpenseDao plannedExpenseDao;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private PlannedExpenseService plannedExpenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseOverPlanRepository expenseOverPlanRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoneyCalculatorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        budgetSpec();
    }

    private void budgetSpec() {
        LocalDate dateFrom = LocalDate.of(2018, 8, 1);
        LocalDate dateTo = LocalDate.of(2018, 8, 31);
        RefId refId = budgetService.createBudget(dateFrom, dateTo);
        plannedExpenseService.createPlannedExpense(refId, dateFrom, dateTo, CREDIT, of(1750, PLN));
        plannedExpenseService.createPlannedExpense(refId, dateFrom, dateTo, FOOD, of(1500, PLN));

        Expense expense = Expense.of(dateFrom.plusDays(1), of(1600, PLN), FOOD);
        System.out.println("Expense id: " + expense.getId());

        expenseRepository.save(expense);
        plannedExpenseService.register(expense);

        List<ExpenseOverPlanRepository.ExpenseOverPlan> expensesOverPlan = expenseOverPlanRepository.findExpensesOverPlan(refId);
        System.out.println("Expenses over plan: " + expensesOverPlan);
    }

    private void test() {
        expenseDao.save(new ExpenseEntity(ExpenseId.of(), Category.FOOD, LocalDate.now(), of(135, PLN)));
        expenseDao.save(new ExpenseEntity(ExpenseId.of(), Category.FOOD, LocalDate.now(), of(300, PLN)));

        plannedExpenseDao.save(new PlannedExpenseEntity(REF_ID, LocalDate.MIN, LocalDate.MAX, Category.FOOD, of(1500, PLN)));
        plannedExpenseDao.save(new PlannedExpenseEntity(REF_ID, LocalDate.MIN, LocalDate.MAX, Category.GAS, of(600, PLN)));

        // plannedExpenseDao.save(new PlannedExpenseEntity(LocalDate.MIN, LocalDate.MAX, Category.FOOD, Money.of(200, Currency.PLN)));

        System.out.println("stuff");
    }
}
