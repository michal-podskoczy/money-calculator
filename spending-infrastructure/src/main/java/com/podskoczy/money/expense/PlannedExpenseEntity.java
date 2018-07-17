package com.podskoczy.money.expense;


import com.podskoczy.money.Money;
import com.podskoczy.money.management.RefId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Michal on 2018-02-05.
 */
@Table(name = "planned_expenses")
@Entity
@Getter
@NoArgsConstructor
public class PlannedExpenseEntity {

    @Id
    @GeneratedValue
    private String id;

    private String refId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Category category;

    private BigDecimal predictionAmount;

    private String predictionCurrency;

    public PlannedExpenseEntity(RefId refId, LocalDate from, LocalDate to, Category category, Money prediction) {
        this.refId = refId.getRefId();
        this.dateFrom = from;
        this.dateTo = to;
        this.category = category;
        this.predictionAmount = prediction.getAmount();
        this.predictionCurrency = prediction.getCurrency().getCode();
    }
}
