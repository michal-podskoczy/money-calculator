package com.podskoczy.money.budget.persistance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "budgets")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class BudgetEntity {

    @Id
    @GeneratedValue
    private String id;

    private String refId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private BigDecimal balanceAmount;

    private String balanceCurrency;

    public BudgetEntity(String refId, LocalDate dateFrom, LocalDate dateTo, BigDecimal balanceAmount, String balanceCurrency) {
        this.refId = refId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.balanceAmount = balanceAmount;
        this.balanceCurrency = balanceCurrency;
    }


}
