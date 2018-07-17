package com.podskoczy.money.expense;

import com.podskoczy.money.Currency;
import com.podskoczy.money.Money;
import com.podskoczy.money.expense.exceed.ExpenseOverPlanMarker;
import com.podskoczy.money.expense.persistance.ExpenseDao;
import com.podskoczy.money.expense.persistance.ExpenseEntity;

import com.podskoczy.money.expense.persistance.PlannedExpenseDao;
import com.podskoczy.money.management.ExpenseId;
import com.podskoczy.money.management.RefId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Michal on 2018-02-05.
 */
@Component
@AllArgsConstructor
public class PlannedExpenseORMRepository implements PlannedExpenseRepository {

    private PlannedExpenseDao plannedExpenseDao;

    private ExpenseDao expenseDao;

    private PlannedExpenseEvents plannedExpenseEvents;

    private ExpenseOverPlanMarker expenseOverPlanMarker;

    private ExpenseRepository expenseRepository;

    @Override
    public Collection<PlannedExpense> get(Category category) {
        Collection<PlannedExpenseEntity> plannedExpenseEntities = plannedExpenseDao.findByCategory(category);
        return map(plannedExpenseEntities);
    }

    @Override
    public Collection<PlannedExpense> get(RefId refId) {
        Collection<PlannedExpenseEntity> plannedExpenseEntities = plannedExpenseDao.findByRefId(refId.getRefId());
        return map(plannedExpenseEntities);
    }

    private List<PlannedExpense> map(Collection<PlannedExpenseEntity> plannedExpenseEntities) {
        return plannedExpenseEntities.stream()
                .map(plannedExpenseEntity -> {
                    List<ExpenseEntity> expenseEntities = expenseDao.findByCategory(plannedExpenseEntity.getCategory());
                    return convert(plannedExpenseEntity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlannedExpense> get(RefId refId, Category category) {

        PlannedExpenseEntity plannedExpenseEntity = plannedExpenseDao.findByCategoryAndRefId(category, refId.getRefId());

        List<ExpenseEntity> expenseEntities = expenseDao.findByCategory(category);

        if (expenseEntities == null) {
            return Optional.empty();
        }

        return Optional.of(convert(plannedExpenseEntity));
    }

    private PlannedExpense convert(PlannedExpenseEntity plannedExpenseEntity) {
        return new PlannedExpense(
                new RefId(plannedExpenseEntity.getRefId()),
                plannedExpenseEntity.getDateFrom(),
                plannedExpenseEntity.getDateTo(),
                plannedExpenseEntity.getCategory(),
                Money.of(plannedExpenseEntity.getPredictionAmount(), Currency.PLN),
                Money.ZERO_PLN,
                expenseRepository,
                plannedExpenseEvents,
                expenseOverPlanMarker);
    }

    private PlannedExpenseEntity map(PlannedExpense plannedExpense) {
        return new PlannedExpenseEntity(
                plannedExpense.getRefId(),
                plannedExpense.from, plannedExpense.to,
                plannedExpense.getCategory(),
                plannedExpense.getPlannedAmount());
    }


    private List<Expense> map(Category category, LocalDate from, LocalDate to, List<ExpenseEntity> expenses) {
        return expenses.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PlannedExpense plannedExpense) {
        PlannedExpenseEntity plannedExpenseEntity = map(plannedExpense);
        plannedExpenseDao.save(plannedExpenseEntity);
    }

    private Expense map(ExpenseEntity expenseEntity) {
        return Expense.of(ExpenseId.of(expenseEntity.getExpenseId()), expenseEntity.getDate(), Money.of(expenseEntity.getAmount(), Currency.PLN), expenseEntity.getCategory());
    }


}
